package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;

public class Tour extends Piece {

    public Tour(int row, int column, boolean isWhite, Joueur joueur) {
        super(row, column, isWhite, joueur);
    }

    @Override
    public boolean isMoveLegal(int row, int column) {
        return row == getColumn() || column == getRow();
    }

}
