package fr.iut.chesscomsae.piece;

public abstract class Piece {

    private final int x;
    private final int y;

    private final boolean isWhite;

    public Piece(int x, int y, boolean isWhite) {
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
    }

    public int getColumn() {
        return x;
    }

    public int getRow() {
        return y;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public abstract boolean isMoveLegal(int x, int y);

}
