package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;
import fr.iut.chesscomsae.Plateau;

import java.util.ArrayList;

public class Cavalier extends Piece{

    public Cavalier(int ligne, int colonne, Joueur joueur) {
        super(ligne, colonne, joueur);
    }

    @Override
    public boolean isMoveLegal(int ligne, int colonne) {
        return (Math.abs(ligne - getColonne()) == 2 && Math.abs(colonne - getLigne()) == 1) || (Math.abs(ligne - getColonne()) == 1 && Math.abs(colonne - getLigne()) == 2);
    }

    @Override
    public String getImage() {
        return estBlanc() ? "file:src/main/resources/pieces/cavalierBlanc.png" : "file:src/main/resources/pieces/cavalierNoir.png";
    }

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
        return mouvements;
    }

    @Override
    public String toString() {
        return "Cavalier[" + super.toString() + "]";
    }

}
