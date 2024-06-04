package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;
import fr.iut.chesscomsae.Plateau;

import java.util.ArrayList;

public class Fou extends Piece {

    public Fou(int ligne, int colonne, Joueur joueur) {
        super(ligne, colonne, joueur);
    }

    @Override
    public boolean isMoveLegal(int ligne, int colonne) {
        return Math.abs(ligne - getColonne()) == Math.abs(colonne - getLigne());
    }

    @Override
    public ArrayList<int[]> mouvementsPossibles(Plateau plateau) {
        // TODO
        return null;
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
