package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;

public class Pion extends Piece {

    public Pion(int x, int y, boolean isWhite, Joueur joueur) {
        super(x, y, isWhite, joueur);
    }

    @Override
    public boolean isMoveLegal(int x, int y) {
        if (isWhite()) return y == getRow() + 1 && x == getColumn();
        return y == getRow() - 1 && x == getColumn();
    }
}
