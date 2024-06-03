package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;

public class Pion extends Piece {

    public Pion(int row, int column, boolean isWhite, Joueur joueur) {
        super(row, column, isWhite, joueur);
    }

    @Override
    public boolean isMoveLegal(int row, int column) {
        if (isWhite()) return row == getRow() && column == getColumn() + 1;
        return row == getRow() && column == getColumn() - 1;
    }
}
