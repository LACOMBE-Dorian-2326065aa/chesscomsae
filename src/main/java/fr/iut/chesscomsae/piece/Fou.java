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
        ArrayList<int[]> mouvements = new ArrayList<>();

        for(int i = 1; i < 8; i++) {
            if(getLigne() + i > 7 || getColonne() + i > 7) break;
            if(plateau.getTableau().get(getLigne()+i).get(getColonne()+i) == null) {
                mouvements.add(new int[]{getLigne()+i,getColonne()+i});
            } else if(plateau.getTableau().get(getLigne()+i).get(getColonne()+i).estBlanc() != estBlanc()) {
                mouvements.add(new int[]{getLigne()+i,getColonne()+i});
                break;
            } else {
                break;
            }
        }

        for(int i = 1; i < 8; i++) {
            if(getLigne() - i < 0 || getColonne() + i > 7) break;
            if(plateau.getTableau().get(getLigne()-i).get(getColonne()+i) == null) {
                mouvements.add(new int[]{getLigne()-i,getColonne()+i});
            } else if(plateau.getTableau().get(getLigne()-i).get(getColonne()+i).estBlanc() != estBlanc()) {
                mouvements.add(new int[]{getLigne()-i,getColonne()+i});
                break;
            } else {
                break;
            }
        }

        for(int i = 1; i < 8; i++) {
            if(getLigne() - i < 0 || getColonne() - i < 0) break;
            if(plateau.getTableau().get(getLigne()-i).get(getColonne()-i) == null) {
                mouvements.add(new int[]{getLigne()-i,getColonne()-i});
            } else if(plateau.getTableau().get(getLigne()-i).get(getColonne()-i).estBlanc() != estBlanc()) {
                mouvements.add(new int[]{getLigne()-i,getColonne()-i});
                break;
            } else {
                break;
            }
        }

        for(int i = 1; i < 8; i++) {
            if(getLigne() + i > 7 || getColonne() - i < 0) break;
            if(plateau.getTableau().get(getLigne()+i).get(getColonne()-i) == null) {
                mouvements.add(new int[]{getLigne()+i,getColonne()-i});
            } else if(plateau.getTableau().get(getLigne()+i).get(getColonne()-i).estBlanc() != estBlanc()) {
                mouvements.add(new int[]{getLigne()+i,getColonne()-i});
                break;
            } else {
                break;
            }
        }

        return mouvements;
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
