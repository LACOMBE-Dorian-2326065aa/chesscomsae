package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;

public class Tour extends Piece {

    public Tour(int ligne, int colonne, boolean estBlanc, Joueur joueur) {
        super(ligne, colonne, estBlanc, joueur);
    }

    @Override
    public boolean isMoveLegal(int row, int column) {
        return row == getColonne() || column == getLigne();
    }

}
