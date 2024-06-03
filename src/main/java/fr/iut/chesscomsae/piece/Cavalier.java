package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;

public class Cavalier extends Piece{

    public Cavalier(int ligne, int colonne, Joueur joueur) {
        super(ligne, colonne, joueur);
    }

    @Override
    public boolean isMoveLegal(int ligne, int colonne) {
        return (Math.abs(ligne - getColonne()) == 2 && Math.abs(colonne - getLigne()) == 1) || (Math.abs(ligne - getColonne()) == 1 && Math.abs(colonne - getLigne()) == 2);
    }
    @Override
    public String getImage() {
        return estBlanc() ? "file:src/main/resources/pieces/cavalierBlanc.png" : "file:src/main/resources/pieces/cavalierNoir.png";
    }

    @Override
    public String toString() {
        return "Cavalier[" + super.toString() + "]";
    }

}
