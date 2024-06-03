package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;

public class Fou extends Piece {

    public Fou(int ligne, int colonne, boolean estBlanc, Joueur joueur) {
        super(ligne, colonne, estBlanc, joueur);
    }

    @Override
    public boolean isMoveLegal(int row, int column) {
        return Math.abs(row - getColonne()) == Math.abs(column - getLigne());
    }
}
