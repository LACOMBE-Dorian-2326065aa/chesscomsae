package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;

public class Fou extends Piece {

    public Fou(int x, int y, boolean isWhite, Joueur joueur) {
        super(x, y, isWhite, joueur);
    }

    @Override
    public boolean isMoveLegal(int x, int y) {
        return Math.abs(x - getColumn()) == Math.abs(y - getRow());
    }
}
