package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;

public class Roi extends Piece {

    public Roi(int ligne, int colonne, Joueur joueur) {
        super(ligne, colonne, joueur);
    }

    @Override
    public boolean isMoveLegal(int ligne, int colonne) {
        return Math.abs(ligne - getColonne()) <= 1 && Math.abs(colonne - getLigne()) <= 1;
    }

    @Override
    public String getImage() {
        return estBlanc() ? "file:src/main/resources/pieces/roiBlanc.png" : "file:src/main/resources/pieces/roiNoir.png";
    }

    @Override
    public String toString() {
        return "Roi[" + super.toString() + "]";
    }
}
