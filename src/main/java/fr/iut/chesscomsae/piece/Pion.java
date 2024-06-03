package fr.iut.chesscomsae.piece;

public class Pion extends Piece {

    public Pion(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }


    @Override
    public boolean isMoveLegal(int x, int y) {
        if (isWhite()) return y == getRow() + 1 && x == getColumn();
        return y == getRow() - 1 && x == getColumn();
    }
}
