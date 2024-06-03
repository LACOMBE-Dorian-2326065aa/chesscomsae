package fr.iut.chesscomsae.piece;

public abstract class Piece {

    public Piece() {

    }

    public abstract boolean isMoveLegal(int x, int y);

}
