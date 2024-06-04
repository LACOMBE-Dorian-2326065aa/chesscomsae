package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;
import fr.iut.chesscomsae.Plateau;

import java.util.ArrayList;

public class Roi extends Piece {

    public Roi(int ligne, int colonne, Joueur joueur) {
        super(ligne, colonne, joueur);
    }

    @Override
    public boolean isMoveLegal(int ligne, int colonne) {
        return Math.abs(ligne - getColonne()) <= 1 && Math.abs(colonne - getLigne()) <= 1;
    }

    @Override
    public ArrayList<int[]> mouvementsPossibles(Plateau plateau) {
        ArrayList<int[]> mouvements = new ArrayList<>();
        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                if (i == 0 && j == 0) continue;
                if (getLigne() + i < 0 || getLigne() + i > 7 || getColonne() + j < 0 || getColonne() + j > 7) continue;
                if (plateau.getTableau().get(getLigne() + i).get(getColonne() + j) == null || plateau.getTableau().get(getLigne() + i).get(getColonne() + j).estBlanc() != estBlanc()) {
                    mouvements.add(new int[]{getLigne() + i, getColonne() + j});
                }
            }
        }
        return mouvements;
    }

    @Override
    public String getImage() {
        return estBlanc() ? "file:src/main/resources/pieces/roiBlanc.png" : "file:src/main/resources/pieces/roiNoir.png";
    }

    @Override
    public String toString() {
        return "Roi[" + super.toString() + "]";
    }
}
