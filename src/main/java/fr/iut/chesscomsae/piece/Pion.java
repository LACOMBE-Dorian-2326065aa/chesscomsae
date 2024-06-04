package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;
import fr.iut.chesscomsae.Plateau;

import java.util.ArrayList;

public class Pion extends Piece {

    private boolean premierCoups;


    public Pion(int ligne, int colonne, Joueur joueur) {
        super(ligne, colonne, joueur);
        premierCoups = true;
    }

    @Override
    public ArrayList<int[]> mouvementsPossibles(Plateau plateau) {

        ArrayList<int[]> mouvements = new ArrayList<>();
        ArrayList<ArrayList<Piece>> tableau = plateau.getTableau();

        int deplacement = 1;
        // Regarde s'il s'agit du premier coup du pion
        if (premierCoups) {
            // Si oui, le pion peut avancer de 2 cases
            deplacement = 2;
        }

        if (estBlanc()) {
            // Ajout des mouvements possibles en avant pour le pion blanc
            for (int i = 1; i <= deplacement; ++i) {
                if (getLigne() - i > 0 && tableau.get(getLigne()-i).get(getColonne()) == null) {
                    mouvements.add(new int[]{getLigne() - i, getColonne()});
                }else {
                    break;
                }
            }

            // Ajout des prises en diagonale lorsqu'elles sont possibles pour le pion blanc
            if (tableau.get(getLigne() - 1).get(getColonne() - 1) != null && tableau.get(getLigne() - 1).get(getColonne() - 1).estBlanc() != estBlanc()) mouvements.add(new int[]{getLigne()-1, getColonne()-1});
            if (tableau.get(getLigne() - 1).get(getColonne() + 1) != null && tableau.get(getLigne() - 1).get(getColonne() + 1).estBlanc() != estBlanc()) mouvements.add(new int[]{getLigne()-1, getColonne()+1});

        }else {
            // Ajout des mouvements possibles en avant pour le pion noir
            for (int i = 1; i <= deplacement; ++i) {
                if (getLigne() + i < 8 && tableau.get(getLigne()+i).get(getColonne()) == null) {
                    mouvements.add(new int[]{getLigne()+i, getColonne()});
                }else {
                    break;
                }
            }

            // Ajout des prises en diagonale lorsqu'elles sont possibles pour le pion noir
            if (tableau.get(getLigne()+1).get(getColonne()-1) != null && tableau.get(getLigne() + 1).get(getColonne()-1).estBlanc() != estBlanc()) mouvements.add(new int[]{getLigne()+1, getColonne()-1});
            if (tableau.get(getLigne()+1).get(getColonne()+1) != null && tableau.get(getLigne() + 1).get(getColonne()+1).estBlanc() != estBlanc()) mouvements.add(new int[]{getLigne()+1, getColonne()+1});

        }
        return mouvements;
    }

    public void setPremierCoups(boolean premierCoups) {
        this.premierCoups = premierCoups;
    }

    public boolean getPremierCoups() {
        return premierCoups;
    }


    @Override
    public String getImage() {
        return estBlanc() ? "file:src/main/resources/pieces/pionBlanc.png" : "file:src/main/resources/pieces/pionNoir.png";
    }

    @Override
    public String toString() {
        return "Pion[" + super.toString() + "]";
    }

}
