package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;

public class Reine extends Piece{

    public Reine(int ligne, int colonne, boolean estBlanc, Joueur joueur) {
        super(ligne, colonne, estBlanc, joueur);
    }

    @Override
    public boolean isMoveLegal(int row, int column) {
        return row == getColonne() || column == getLigne() || Math.abs(row - getColonne()) == Math.abs(column - getLigne());
    }

}
