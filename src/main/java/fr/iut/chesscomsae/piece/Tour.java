package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;

public class Tour extends Piece {

    public Tour(int ligne, int colonne, Joueur joueur) {
        super(ligne, colonne, joueur);
    }

    @Override
    public boolean isMoveLegal(int ligne, int colonne) {
        return ligne == getColonne() || colonne == getLigne();
    }

    @Override
    public String getImage() {
        return null;
    }

    @Override
    public String toString() {
        return "Tour[" + super.toString() + "]";
    }

}
