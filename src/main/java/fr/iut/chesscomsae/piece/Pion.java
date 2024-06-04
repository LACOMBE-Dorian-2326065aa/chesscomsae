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
            premierCoups = false;
            deplacement = 2;
        }

        // S'il est noir, il doit avancer vers le bas
        if (estBlanc()) {
            deplacement = -deplacement;
        }

        // Ajout des mouvements possible en ligne droite
        for (int i = 1; i <= deplacement; ++i) {
            if (tableau.get(getLigne() + i).get(getColonne()) == null) {
                mouvements.add(new int[]{getLigne() + i, getColonne()});
            } else {
                break;
            }
        }
        // Changement du déplacement possible
        if (estBlanc()) {
            deplacement = -1;
        }else {
            deplacement = 1;
        }

        // Ajout de la fonction pour manger en diagonale
        for (int i = -1; i <= 1; ++i) {
            if (i == 0) continue;
            if (tableau.get(getLigne() + deplacement).get(getColonne()+i) != null && tableau.get(getLigne() + deplacement).get(getColonne()+i).estBlanc() != estBlanc()) {
                mouvements.add(new int[]{getLigne()+deplacement, getColonne()+i});
            }
        }
        return mouvements;
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
