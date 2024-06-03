package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;

public class Reine extends Piece{

    public Reine(int row, int column, boolean isWhite, Joueur joueur) {
        super(row, column, isWhite, joueur);
    }

    @Override
    public boolean isMoveLegal(int row, int column) {
        return row == getColumn() || column == getRow() || Math.abs(row - getColumn()) == Math.abs(column - getRow());
    }

}
