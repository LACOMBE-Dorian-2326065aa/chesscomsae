package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;

public class Roi extends Piece {

    public Roi(int ligne, int colonne, boolean estBlanc, Joueur joueur) {
        super(ligne, colonne, joueur);
    }

    @Override
    public boolean isMoveLegal(int row, int column) {
        return Math.abs(row - getColonne()) <= 1 && Math.abs(column - getLigne()) <= 1;
    }

    @Override
    public String getImage() {
        return null;
    }

    @Override
    public String toString() {
        return "Roi[" + super.toString() + "]";
    }
}
