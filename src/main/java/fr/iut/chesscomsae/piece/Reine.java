package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;

public class Reine extends Piece{

    public Reine(int ligne, int colonne, Joueur joueur) {
        super(ligne, colonne, joueur);
    }

    @Override
    public boolean isMoveLegal(int ligne, int colonne) {
        return ligne == getColonne() || colonne == getLigne() || Math.abs(ligne - getColonne()) == Math.abs(colonne - getLigne());
    }

    @Override
    public String getImage() {
        return null;
    }

    @Override
    public String toString() {
        return "Reine[" + super.toString() + "]";
    }

}
