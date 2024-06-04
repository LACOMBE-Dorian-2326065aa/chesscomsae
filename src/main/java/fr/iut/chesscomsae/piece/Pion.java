package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;
import fr.iut.chesscomsae.Plateau;

import java.util.ArrayList;

public class Pion extends Piece {

    private boolean premierCoups;


    public Pion(int ligne, int colonne, Joueur joueur) {
        super(ligne, colonne, joueur);
    }

    @Override
    public boolean isMoveLegal(int ligne, int colonne) {
        if (estBlanc()) return ligne == getLigne() && colonne == getColonne() + 1;
        return ligne == getLigne() && colonne == getColonne() - 1;
    }


    public ArrayList<int[]> mouvementPossible(Plateau plateau) {
        ArrayList<int[]> mouvement = new ArrayList<>();
        int[] deplacement;
        ArrayList<int[]> mouvements = null;

        switch (premierCoups ? 2 : 1) {
            // Si c'est le premier deplacement du pion, il peut avancer de deux cases
            case 2:
                if (estBlanc()) {
                    deplacement = new int[]{getLigne(), getColonne() + 2};
                } else {
                    deplacement = new int[]{getLigne(), getColonne() - 2};
                }
                if (plateau.getTableau().get(deplacement[0]).get(deplacement[1]) == null) {
                    mouvements.add(deplacement);
                }
                // Maintenant on peut avancer que d'une case apres le premier deplacement du pion.
            case 1:
                if (estBlanc()) {
                    deplacement = new int[]{getLigne(), getColonne() + 1};
                } else {
                    deplacement = new int[]{getLigne(), getColonne() - 1};
                }
                if (plateau.getTableau().get(deplacement[0]).get(deplacement[1]) == null) {
                    mouvements.add(deplacement);
                }
                // Check la capture de la piece a gauche du pion
                if (getColonne() > 0) {
                    deplacement = new int[]{getLigne() - 1, getColonne() - 1};
                    if (plateau.getTableau().get(deplacement[0]).get(deplacement[1]) != null &&
                            plateau.getTableau().get(deplacement[0]).get(deplacement[1]).estBlanc() != this.estBlanc()) {
                        mouvements.add(deplacement);
                    }
                }
                //  Check la capture de la piece a droite du pion
                if (getColonne() < 7) {
                    deplacement = new int[]{getLigne() - 1, getColonne() + 1};
                    if (plateau.getTableau().get(deplacement[0]).get(deplacement[1]) != null &&
                            plateau.getTableau().get(deplacement[0]).get(deplacement[1]).estBlanc() != this.estBlanc()) {
                        mouvements.add(deplacement);
                    }
                }

                break;
        }

        premierCoups = false; // premierCoups qui devient false après le premier déplacement
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
