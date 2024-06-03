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
        return estBlanc() ? "file:src/main/resources/pieces/tourBlanc.png" : "file:src/main/resources/pieces/tourNoir.png";
    }

    @Override
    public String toString() {
        return "Tour[" + super.toString() + "]";
    }

}
