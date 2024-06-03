package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;

public class Pion extends Piece {

    public Pion(int ligne, int colonne, boolean estBlanc, Joueur joueur) {
        super(ligne, colonne, estBlanc, joueur);
    }

    @Override
    public boolean isMoveLegal(int row, int column) {
        if (estBlanc()) return row == getLigne() && column == getColonne() + 1;
        return row == getLigne() && column == getColonne() - 1;
    }
}
