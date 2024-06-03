package fr.iut.chesscomsae.piece;

public class Cavalier extends Piece{

        public Cavalier(int x, int y, boolean isWhite) {
            super(x, y, isWhite);
        }

        @Override
        public boolean isMoveLegal(int x, int y) {
            return (Math.abs(x - getColumn()) == 2 && Math.abs(y - getRow()) == 1) || (Math.abs(x - getColumn()) == 1 && Math.abs(y - getRow()) == 2);
        }
}
