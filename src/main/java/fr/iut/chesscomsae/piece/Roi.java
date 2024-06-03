package fr.iut.chesscomsae.piece;

public class Roi extends Piece {

    public Roi(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    @Override
    public boolean isMoveLegal(int x, int y) {
        return Math.abs(x - getColumn()) <= 1 && Math.abs(y - getRow()) <= 1;
    }
}
