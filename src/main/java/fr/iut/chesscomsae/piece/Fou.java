package fr.iut.chesscomsae.piece;

public class Fou extends Piece {

    public Fou(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    @Override
    public boolean isMoveLegal(int x, int y) {
        return Math.abs(x - getColumn()) == Math.abs(y - getRow());
    }
}
