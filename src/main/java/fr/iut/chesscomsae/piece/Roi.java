package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;

public class Roi extends Piece {

    public Roi(int x, int y, boolean isWhite, Joueur joueur) {
        super(x, y, isWhite, joueur);
    }

    @Override
    public boolean isMoveLegal(int x, int y) {
        return Math.abs(x - getColumn()) <= 1 && Math.abs(y - getRow()) <= 1;
    }
}
