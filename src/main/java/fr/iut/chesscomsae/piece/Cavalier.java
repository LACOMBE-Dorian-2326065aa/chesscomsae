package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;
import fr.iut.chesscomsae.Plateau;

import java.util.ArrayList;

public class Cavalier extends Piece{

    /**
     * Constructeur de la classe Cavalier
     * @author Valent Hugo
     * @param ligne Ligne de la pièce
     * @param colonne Colonne de la pièce
     * @param joueur Joueur de la pièce
     */
    public Cavalier(int ligne, int colonne, Joueur joueur) {
        super(ligne, colonne, joueur);
    }

    /**
     * Permet de récupérer l'image de la pièce
     * @author Valent Hugo
     * @return Image de la pièce
     */
    @Override
    public String getImage() {
        return estBlanc() ? "file:src/main/resources/pieces/cavalierBlanc.png" : "file:src/main/resources/pieces/cavalierNoir.png";
    }

    /**
     * Permet de récupérer les mouvements possibles de la pièce Cavalier sur le plateau
     * @author Valente Hugo
     * @return Liste des mouvements possibles de la pièce Cavalier
     */
    @Override
    public ArrayList<int[]> mouvementsPossibles(Plateau plateau) {
        ArrayList<int[]> mouvements = new ArrayList<>();
        // On vérifie les 8 mouvements possibles du cavalier
        for (int i = 0; i < 8; ++i) {
            int[] mouvement = new int[2];
            switch (i) {
                case 0 -> {
                    mouvement[0] = getLigne() + 2;
                    mouvement[1] = getColonne() + 1;
                }
                case 1 -> {
                    mouvement[0] = getLigne() + 2;
                    mouvement[1] = getColonne() - 1;
                }
                case 2 -> {
                    mouvement[0] = getLigne() - 2;
                    mouvement[1] = getColonne() + 1;
                }
                case 3 -> {
                    mouvement[0] = getLigne() - 2;
                    mouvement[1] = getColonne() - 1;
                }
                case 4 -> {
                    mouvement[0] = getLigne() + 1;
                    mouvement[1] = getColonne() + 2;
                }
                case 5 -> {
                    mouvement[0] = getLigne() + 1;
                    mouvement[1] = getColonne() - 2;
                }
                case 6 -> {
                    mouvement[0] = getLigne() - 1;
                    mouvement[1] = getColonne() + 2;
                }
                case 7 -> {
                    mouvement[0] = getLigne() - 1;
                    mouvement[1] = getColonne() - 2;
                }
            }
            // Vérifie si le mouvement est possible pour savoir si le mouvement doit être ajouté au mouvement possible
            if (mouvement[0] >= 0
                    && mouvement[0] < 8
                    && mouvement[1] >= 0
                    && mouvement[1] < 8
                    && (plateau.getTableau().get(mouvement[0]).get(mouvement[1]) == null
                    || plateau.getTableau().get(mouvement[0]).get(mouvement[1]).estBlanc() != estBlanc())) {
                mouvements.add(mouvement);
            }
        }

        mouvements = plateau.filtreEchec(mouvements, estBlanc());


        return plateau.canPieceMove(this, estBlanc()) ? mouvements : new ArrayList<int[]>();
        //return mouvements;
    }

    /**
     * Permet de récupérer les mouvements possibles de la pièce Cavalier sur le plateau, y compris les protections d'alliés
     * @author Valente Hugo
     * @return Liste des mouvements possibles de la pièce Cavalier
     */
    @Override
    public ArrayList<int[]> mouvementsPossiblesEchecEtMat(Plateau plateau) {
        ArrayList<int[]> mouvements = new ArrayList<>();
        // On vérifie les 8 mouvements possibles du cavalier
        for (int i = 0; i < 8; ++i) {
            int[] mouvement = new int[2];
            switch (i) {
                case 0 -> {
                    mouvement[0] = getLigne() + 2;
                    mouvement[1] = getColonne() + 1;
                }
                case 1 -> {
                    mouvement[0] = getLigne() + 2;
                    mouvement[1] = getColonne() - 1;
                }
                case 2 -> {
                    mouvement[0] = getLigne() - 2;
                    mouvement[1] = getColonne() + 1;
                }
                case 3 -> {
                    mouvement[0] = getLigne() - 2;
                    mouvement[1] = getColonne() - 1;
                }
                case 4 -> {
                    mouvement[0] = getLigne() + 1;
                    mouvement[1] = getColonne() + 2;
                }
                case 5 -> {
                    mouvement[0] = getLigne() + 1;
                    mouvement[1] = getColonne() - 2;
                }
                case 6 -> {
                    mouvement[0] = getLigne() - 1;
                    mouvement[1] = getColonne() + 2;
                }
                case 7 -> {
                    mouvement[0] = getLigne() - 1;
                    mouvement[1] = getColonne() - 2;
                }
            }
            // Vérifie si le mouvement est possible pour savoir si le mouvement doit être ajouté au mouvement possible
            if (mouvement[0] >= 0
                    && mouvement[0] < 8
                    && mouvement[1] >= 0
                    && mouvement[1] < 8) {
                mouvements.add(mouvement);
            }
        }
        return mouvements;
    }

    public ArrayList<int[]> getPathToKing(Plateau plateau) {
        ArrayList<int[]> path = new ArrayList<>();
        path.add(new int[]{getLigne(), getColonne()});
        for (int i = 0; i < 8; ++i) {
            int[] mouvement = new int[2];
            switch (i) {
                case 0 -> {
                    mouvement[0] = getLigne() + 2;
                    mouvement[1] = getColonne() + 1;
                }
                case 1 -> {
                    mouvement[0] = getLigne() + 2;
                    mouvement[1] = getColonne() - 1;
                }
                case 2 -> {
                    mouvement[0] = getLigne() - 2;
                    mouvement[1] = getColonne() + 1;
                }
                case 3 -> {
                    mouvement[0] = getLigne() - 2;
                    mouvement[1] = getColonne() - 1;
                }
                case 4 -> {
                    mouvement[0] = getLigne() + 1;
                    mouvement[1] = getColonne() + 2;
                }
                case 5 -> {
                    mouvement[0] = getLigne() + 1;
                    mouvement[1] = getColonne() - 2;
                }
                case 6 -> {
                    mouvement[0] = getLigne() - 1;
                    mouvement[1] = getColonne() + 2;
                }
                case 7 -> {
                    mouvement[0] = getLigne() - 1;
                    mouvement[1] = getColonne() - 2;
                }
            }
            // Vérifie si le mouvement est possible pour savoir si le mouvement doit être ajouté au mouvement possible
            if (mouvement[0] >= 0
                    && mouvement[0] < 8
                    && mouvement[1] >= 0
                    && mouvement[1] < 8
                    && plateau.getTableau().get(mouvement[0]).get(mouvement[1]) instanceof Roi
                    && plateau.getTableau().get(mouvement[0]).get(mouvement[1]).estBlanc() != estBlanc()) {
                path.add(mouvement);
            }
        }
        return path;
    }

    /**
     * Permet de récupérer le nom de la pièce Cavalier
     * @author Valente Hugo
     * @return Nom de la pièce Cavalier
     */
    @Override
    public String toString() {
        return "Cavalier[" + super.toString() + "]";
    }

}
