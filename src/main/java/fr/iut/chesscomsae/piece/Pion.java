package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;

public class Pion extends Piece {

    public Pion(int ligne, int colonne, Joueur joueur) {
        super(ligne, colonne, joueur);
    }

    @Override
    public boolean isMoveLegal(int ligne, int colonne) {
        if (estBlanc()) return ligne == getLigne() && colonne == getColonne() + 1;
        return ligne == getLigne() && colonne == getColonne() - 1;
    }

    @Override
    public String getImage() {
        return null;
    }

    @Override
    public String toString() {
        return "Pion[" + super.toString() + "]";
    }
}
