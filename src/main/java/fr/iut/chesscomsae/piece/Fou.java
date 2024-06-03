package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;

public class Fou extends Piece {

    public Fou(int ligne, int colonne, Joueur joueur) {
        super(ligne, colonne, joueur);
    }

    @Override
    public boolean isMoveLegal(int ligne, int colonne) {
        return Math.abs(ligne - getColonne()) == Math.abs(colonne - getLigne());
    }

    @Override
    public String getImage() {
        return estBlanc() ? "file:src/main/resources/pieces/fouBlanc.png" : "file:src/main/resources/pieces/fouNoir.png";
    }

    @Override
    public String toString() {
        return "Fou[" + super.toString() + "]";
    }

}
