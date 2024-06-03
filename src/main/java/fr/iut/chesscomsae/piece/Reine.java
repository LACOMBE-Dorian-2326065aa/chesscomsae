package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;

public class Reine extends Piece{

    public Reine(int x, int y, boolean isWhite, Joueur joueur) {
        super(x, y, isWhite, joueur);
    }

    @Override
    public boolean isMoveLegal(int x, int y) {
        return x == getColumn() || y == getRow() || Math.abs(x - getColumn()) == Math.abs(y - getRow());
    }

}
