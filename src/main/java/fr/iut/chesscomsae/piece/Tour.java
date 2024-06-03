package fr.iut.chesscomsae.piece;

public class Tour extends Piece {

    public Tour(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    @Override
    public boolean isMoveLegal(int x, int y) {
        return x == getColumn() || y == getRow();
    }

}
