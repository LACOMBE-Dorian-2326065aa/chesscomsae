package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;
import fr.iut.chesscomsae.Plateau;

import java.util.ArrayList;

public class Tour extends Piece {

    /**
     * Constructeur de la classe Tour
     * @author Valente Hugo
     * @param ligne Ligne de la pièce
     * @param colonne Colonne de la pièce
     * @param joueur Joueur de la pièce
     */
    public Tour(int ligne, int colonne, Joueur joueur) {
        super(ligne, colonne, joueur);
    }

    /**
     * Permet de récupérer les mouvements possibles de la pièce Tour sur le plateau
     * @author Valente Hugo
     * @param plateau Plateau de jeu
     * @return Liste des mouvements possibles de la pièce Tour
     */
    @Override
    public ArrayList<int[]> mouvementsPossibles(Plateau plateau) {
        ArrayList<int[]> mouvements = new ArrayList<>();
        // On vérifie pour les 4 sens possibles de la tour
        // Sens vers le bas
        if (!(getLigne() + 1 > 7)) {
            for (int j = getLigne() + 1; j < 8; ++j) {
                if (conditionLigne(plateau, mouvements, j)) break;
            }
        }
        // Sens vers le haut
        if (!(getLigne() - 1 < 0)) {
            for (int j = getLigne() - 1; j >= 0; --j) {
                if (conditionLigne(plateau, mouvements, j)) break;
            }
        }
        // Sens vers la droite
        if (!(getColonne() + 1 > 7)) {
            for (int j = getColonne() + 1; j < 8; ++j) {
                if (conditionColonne(plateau, mouvements, j)) break;
            }
        }
        // Sens vers la gauche
        if (!(getColonne() - 1 < 0)) {
            for (int j = getColonne() - 1; j >= 0; --j) {
                if (conditionColonne(plateau, mouvements, j)) break;
            }
        }

        return mouvements;
    }

    /**
     * Permet de vérifier si la colonne est valide pour le mouvement de la tour
     * @author Valente Hugo
     * @param plateau Plateau de jeu
     * @param mouvements Listes des mouvements possibles
     * @param j Colonne
     * @return Vrai si la colonne est valide, faux sinon
     */
    private boolean conditionColonne(Plateau plateau, ArrayList<int[]> mouvements, int j) {
        if (plateau.getTableau().get(getLigne()).get(j) == null) {
            mouvements.add(new int[]{getLigne(), j});
        }else if (plateau.getTableau().get(getLigne()).get(j).estBlanc() != estBlanc()) {
            mouvements.add(new int[]{getLigne(), j});
            return true;
        }else {
            return true;
        }
        return false;
    }

    /**
     * Permet de vérifier si la ligne est valide pour le mouvement de la tour
     * @author Valente Hugo
     * @param plateau Plateau de jeu
     * @param mouvements Listes des mouvements possibles
     * @param j Ligne
     * @return Vrai si la ligne est valide, faux sinon
     */
    private boolean conditionLigne(Plateau plateau, ArrayList<int[]> mouvements, int j) {
        if (plateau.getTableau().get(j).get(getColonne()) == null) {
            mouvements.add(new int[]{j, getColonne()});
        } else if (plateau.getTableau().get(j).get(getColonne()).estBlanc() != estBlanc()) {
            mouvements.add(new int[]{j, getColonne()});
            return true;
        } else {
            return true;
        }
        return false;
    }

    /**
     * Permet de récupérer le chemin de l'image de la pièce Tour en fonction de sa couleur
     * @author Valente Hugo
     * @return Chemin de l'image de la pièce Tour
     */
    @Override
    public String getImage() {
        return estBlanc() ? "file:src/main/resources/pieces/tourBlanc.png" : "file:src/main/resources/pieces/tourNoir.png";
    }

    /**
     * Permet de récupérer la valeur de la pièce Tour
     * @author Valente Hugo
     * @return Valeur de la pièce Tour
     */
    @Override
    public String toString() {
        return "Tour[" + super.toString() + "]";
    }

}
