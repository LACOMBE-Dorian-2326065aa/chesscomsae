package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;
import fr.iut.chesscomsae.Plateau;

import java.util.ArrayList;

public class Roi extends Piece {

    /**
     * Constructeur de la classe Roi
     * @author Valent Hugo
     * @param ligne Ligne de la pièce
     * @param colonne Colonne de la pièce
     * @param joueur Joueur de la pièce
     */
    public Roi(int ligne, int colonne, Joueur joueur) {
        super(ligne, colonne, joueur);
    }


    /**
     * Permet de récupérer les mouvements possibles de la pièce Roi sur le plateau
     * @author Valent Hugo
     * @param plateau Plateau de jeu
     * @return Liste des mouvements possibles de la pièce Roi
     */
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

    /**
     * Permet de récupérer les mouvements possibles de la pièce Roi sur le plateau, y compris les protections d'alliés
     * @author Valent Hugo
     * @param plateau Plateau de jeu
     * @return Liste des mouvements possibles de la pièce Roi
     */
    @Override
    public ArrayList<int[]> mouvementsPossiblesEchecEtMat(Plateau plateau) {
        ArrayList<int[]> mouvements = new ArrayList<>();
        for (int i = -1; i < 2; ++i) {
            mouvements.add(new int[]{getLigne() + 1, getColonne() + i});
            mouvements.add(new int[]{getLigne() - 1, getColonne() + i});
        }
        mouvements.add(new int[]{getLigne(), getColonne() + 1});
        mouvements.add(new int[]{getLigne(), getColonne() - 1});

        for (int i = 0; i < mouvements.size(); ++i) {
            int[] mouvement = mouvements.get(i);
            if (mouvement[0] < 0 || mouvement[0] > 7 || mouvement[1] < 0 || mouvement[1] > 7) {
                mouvements.remove(i);
                --i;
            }
        }

        return mouvements;
    }

    public ArrayList<int[]> getPathToKing(Plateau plateau) {
        return null;
    }

    /**
     * Permet de récupérer l'image de la pièce Roi
     * @author Valent Hugo
     * @return Image de la pièce Roi
     */
    @Override
    public String getImage() {
        return estBlanc() ? "file:src/main/resources/pieces/roiBlanc.png" : "file:src/main/resources/pieces/roiNoir.png";
    }

    /**
     * Permet de récupérer la valeur de la pièce Roi
     * @author Valent Hugo
     * @return Valeur de la pièce Roi
     */
    @Override
    public String toString() {
        return "Roi[" + super.toString() + "]";
    }
}
