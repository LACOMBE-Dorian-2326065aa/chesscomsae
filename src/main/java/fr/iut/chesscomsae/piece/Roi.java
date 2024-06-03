package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;

public class Roi extends Piece {

    public Roi(int row, int column, boolean isWhite, Joueur joueur) {
        super(row, column, isWhite, joueur);
    }

    @Override
    public boolean isMoveLegal(int row, int column) {
        return Math.abs(row - getColumn()) <= 1 && Math.abs(column - getRow()) <= 1;
    }
}
