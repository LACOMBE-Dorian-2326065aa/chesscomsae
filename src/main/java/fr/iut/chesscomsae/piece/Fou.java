package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;

public class Fou extends Piece {

    public Fou(int row, int column, boolean isWhite, Joueur joueur) {
        super(row, column, isWhite, joueur);
    }

    @Override
    public boolean isMoveLegal(int row, int column) {
        return Math.abs(row - getColumn()) == Math.abs(column - getRow());
    }
}
