package fr.iut.chesscomsae.piece;

public class Reine extends Piece{

    public Reine(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    @Override
    public boolean isMoveLegal(int x, int y) {
        return x == getColumn() || y == getRow() || Math.abs(x - getColumn()) == Math.abs(y - getRow());
    }

}
