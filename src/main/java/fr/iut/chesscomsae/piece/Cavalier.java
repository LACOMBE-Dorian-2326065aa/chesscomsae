package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;
import javafx.scene.image.Image;

public class Cavalier extends Piece{

    public Cavalier(int ligne, int colonne, boolean estBlanc, Joueur joueur) {
        super(ligne, colonne, joueur);
    }

    @Override
    public boolean isMoveLegal(int row, int column) {
        return (Math.abs(row - getColonne()) == 2 && Math.abs(column - getLigne()) == 1) || (Math.abs(row - getColonne()) == 1 && Math.abs(column - getLigne()) == 2);
    }
    @Override
    public String getImage() {
        return estBlanc() ? "img/piece/cavalierBlanc.png" : "img/piece/cavalierNoir.png" ;
    }

    @Override
    public String toString() {
        return "Cavalier[" + super.toString() + "]";
    }

}
