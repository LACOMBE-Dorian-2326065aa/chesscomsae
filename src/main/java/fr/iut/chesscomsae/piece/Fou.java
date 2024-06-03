package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;

public class Fou extends Piece {

    public Fou(int ligne, int colonne, boolean estBlanc, Joueur joueur) {
        super(ligne, colonne, joueur);
    }

    @Override
    public boolean isMoveLegal(int row, int column) {
        return Math.abs(row - getColonne()) == Math.abs(column - getLigne());
    }

    @Override
    public String getImage() {
        return null;
    }

    @Override
    public String toString() {
        return "Fou[" + super.toString() + "]";
    }

}
