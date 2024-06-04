package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;
import fr.iut.chesscomsae.Plateau;

import java.util.ArrayList;

public class Tour extends Piece {

    public Tour(int ligne, int colonne, Joueur joueur) {
        super(ligne, colonne, joueur);
    }

    @Override
    public boolean isMoveLegal(int ligne, int colonne) {
        return (colonne == getColonne() && ligne != getColonne())|| (colonne != getColonne() && ligne == getColonne());
    }

    @Override
    public ArrayList<int[]> mouvementsPossibles(Plateau plateau) {
        ArrayList<int[]> mouvements = new ArrayList<>();
        ArrayList<ArrayList<Piece>> tableau = plateau.getTableau();
        // On vérifie pour les 4 sens possibles de la tour
        for (int i = 0; i < 4; ++i) {
            // Pour chaque sens possible
            switch (i) {
                // Sens vers le bas
                case 0 -> {
                    if (getLigne() + 1 > 7) continue;
                    for (int j = getLigne() + 1; j < 8; ++j) {
                        if (conditionLigne(plateau, mouvements, j)) break;
                    }
                }
                // Sens vers le haut
                case 1 -> {
                    if (getLigne() - 1 < 0) continue;
                    for (int j = getLigne() - 1; j >= 0; --j) {
                        if (conditionLigne(plateau, mouvements, j)) break;
                    }
                }
                // Sens vers la droite
                case 2 -> {
                    if (getColonne() + 1 > 7) continue;
                    for (int j = getColonne() + 1; j < 8; ++j) {
                        if (conditionColonne(plateau, mouvements, j)) break;
                    }
                }
                // Sens vers la gauche
                case 3 -> {
                    if (getColonne() - 1 < 0) continue;
                    for (int j = getColonne() - 1; j >= 0; --j) {
                        if (conditionColonne(plateau, mouvements, j)) break;
                    }
                }
            }
        }
        return mouvements;
    }

    /**
     * Permet de vérifier si la colonne est valide pour le mouvement de la tour
     * @param plateau Plateau de jeu
     * @param mouvements Listes des mouvements possibles
     * @param j Colonne
     * @return Vrai si la colonne est valide, faux sinon
     */
    private boolean conditionColonne(Plateau plateau, ArrayList<int[]> mouvements, int j) {
        if (plateau.getTableau().get(getLigne()).get(j) == null) {
            mouvements.add(new int[]{getLigne(), j});
        }else if (plateau.getTableau().get(getLigne()).get(j).estBlanc() != estBlanc()) {
            mouvements.add(new int[]{getLigne(), j});
            return true;
        }else {
            return true;
        }
        return false;
    }

    /**
     * Permet de vérifier si la ligne est valide pour le mouvement de la tour
     * @param plateau Plateau de jeu
     * @param mouvements Listes des mouvements possibles
     * @param j Ligne
     * @return Vrai si la ligne est valide, faux sinon
     */
    private boolean conditionLigne(Plateau plateau, ArrayList<int[]> mouvements, int j) {
        if (plateau.getTableau().get(j).get(getColonne()) == null) {
            mouvements.add(new int[]{j, getColonne()});
        } else if (plateau.getTableau().get(j).get(getColonne()).estBlanc() != estBlanc()) {
            mouvements.add(new int[]{j, getColonne()});
            return true;
        } else {
            return true;
        }
        return false;
    }


    @Override
    public String getImage() {
        return estBlanc() ? "file:src/main/resources/pieces/tourBlanc.png" : "file:src/main/resources/pieces/tourNoir.png";
    }

    @Override
    public String toString() {
        return "Tour[" + super.toString() + "]";
    }

}
