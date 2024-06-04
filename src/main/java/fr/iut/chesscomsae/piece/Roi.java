package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;
import fr.iut.chesscomsae.Plateau;

import java.util.ArrayList;

public class Roi extends Piece {

    public Roi(int ligne, int colonne, Joueur joueur) {
        super(ligne, colonne, joueur);
    }


    @Override
    public ArrayList<int[]> mouvementsPossibles(Plateau plateau) {
        ArrayList<int[]> mouvements = new ArrayList<>();
        for (int i = -1; i < 2; ++i) {
            mouvements.add(new int[]{getLigne() + 1, getColonne() + i});
            mouvements.add(new int[]{getLigne() - 1, getColonne() + i});
        }
        mouvements.add(new int[]{getLigne(), getColonne() + 1});
        mouvements.add(new int[]{getLigne(), getColonne() - 1});

        for (int i = 0; i < mouvements.size(); ++i) {
            int[] mouvement = mouvements.get(i);
            if (mouvement[0] < 0 || mouvement[0] > 7 || mouvement[1] < 0 || mouvement[1] > 7 || (plateau.getTableau().get(mouvement[0]).get(mouvement[1]) != null && plateau.getTableau().get(mouvement[0]).get(mouvement[1]).estBlanc() == estBlanc())) {
                mouvements.remove(i);
                --i;
            }
        }

        return mouvements;
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
