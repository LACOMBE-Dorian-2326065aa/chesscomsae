package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;
import fr.iut.chesscomsae.Plateau;

import java.util.ArrayList;

public class Reine extends Piece{

    /**
     * Constructeur de la classe Reine
     * @author Lacombe Dorian
     * @author Valente Hugo
     * @param ligne Ligne de la pièce
     * @param colonne Colonne de la pièce
     * @param joueur Joueur de la pièce
     */
    public Reine(int ligne, int colonne, Joueur joueur) {
        super(ligne, colonne, joueur);
    }

    /**
     * Permet de récupérer les mouvements possibles de la pièce Reine sur le plateau
     * @author Lacombe Dorian
     * @author Valente Hugo
     * @param plateau Plateau de jeu
     * @return Liste des mouvements possibles de la pièce Reine
     */
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

        if (!(getLigne() + 1 > 7)) {
            for (int j = getLigne() + 1; j < 8; ++j) {
                if (conditionLigne(plateau, mouvements, j)) break;
            }
        }
        // Sens vers le haut
        if (!(getLigne() - 1 < 0)) {
            for (int j = getLigne() - 1; j >= 0; --j) {
                if (conditionLigne(plateau, mouvements, j)) break;
            }
        }
        // Sens vers la droite
        if (!(getColonne() + 1 > 7)) {
            for (int j = getColonne() + 1; j < 8; ++j) {
                if (conditionColonne(plateau, mouvements, j)) break;
            }
        }
        // Sens vers la gauche
        if (!(getColonne() - 1 < 0)) {
            for (int j = getColonne() - 1; j >= 0; --j) {
                if (conditionColonne(plateau, mouvements, j)) break;
            }
        }

        mouvements = plateau.filtreEchec(mouvements, estBlanc());


        return plateau.canPieceMove(this, estBlanc()) ? mouvements : new ArrayList<int[]>();
        //return mouvements;
    }

    /**
     * Permet de récupérer les mouvements possibles de la pièce Reine sur le plateau, y compris les protections
     * @author Lacombe Dorian
     * @author Valente Hugo
     * @param plateau Plateau de jeu
     * @return Liste des mouvements possibles de la pièce Reine
     */
    @Override
    public ArrayList<int[]> mouvementsPossiblesEchecEtMat(Plateau plateau) {
        ArrayList<int[]> mouvements = new ArrayList<>();

        for(int i = 1; i < 8; i++) {
            if(getLigne() + i > 7 || getColonne() + i > 7) break;
            if(plateau.getTableau().get(getLigne()+i).get(getColonne()+i) == null || (plateau.getTableau().get(getLigne()+i).get(getColonne()+i) instanceof Roi && plateau.getTableau().get(getLigne()+i).get(getColonne()+i).estBlanc() != estBlanc())) {
                mouvements.add(new int[]{getLigne()+i,getColonne()+i});
            } else {
                mouvements.add(new int[]{getLigne()+i,getColonne()+i});
                break;
            }
        }

        for(int i = 1; i < 8; i++) {
            if(getLigne() - i < 0 || getColonne() + i > 7) break;
            if(plateau.getTableau().get(getLigne()-i).get(getColonne()+i) == null || (plateau.getTableau().get(getLigne()-i).get(getColonne()+i) instanceof Roi && plateau.getTableau().get(getLigne()-i).get(getColonne()+i).estBlanc() != estBlanc())) {
                mouvements.add(new int[]{getLigne()-i,getColonne()+i});
            } else {
                mouvements.add(new int[]{getLigne() - i, getColonne() + i});
                break;
            }
        }

        for(int i = 1; i < 8; i++) {
            if(getLigne() - i < 0 || getColonne() - i < 0) break;
            if(plateau.getTableau().get(getLigne()-i).get(getColonne()-i) == null || (plateau.getTableau().get(getLigne()-i).get(getColonne()-i) instanceof Roi && plateau.getTableau().get(getLigne()-i).get(getColonne()-i).estBlanc() != estBlanc())) {
                mouvements.add(new int[]{getLigne()-i,getColonne()-i});
            } else {
                mouvements.add(new int[]{getLigne()-i,getColonne()-i});
                break;
            }
        }

        for(int i = 1; i < 8; i++) {
            if(getLigne() + i > 7 || getColonne() - i < 0) break;
            if(plateau.getTableau().get(getLigne()+i).get(getColonne()-i) == null || (plateau.getTableau().get(getLigne()+i).get(getColonne()-i) instanceof Roi && plateau.getTableau().get(getLigne()+i).get(getColonne()-i).estBlanc() != estBlanc())) {
                mouvements.add(new int[]{getLigne()+i,getColonne()-i});
            } else {
                mouvements.add(new int[]{getLigne()+i,getColonne()-i});
                break;
            }
        }

        if (!(getLigne() + 1 > 7)) {
            for (int j = getLigne() + 1; j < 8; ++j) {
                if (conditionLigneEM(plateau, mouvements, j)) break;
            }
        }
        // Sens vers le haut
        if (!(getLigne() - 1 < 0)) {
            for (int j = getLigne() - 1; j >= 0; --j) {
                if (conditionLigneEM(plateau, mouvements, j)) break;
            }
        }
        // Sens vers la droite
        if (!(getColonne() + 1 > 7)) {
            for (int j = getColonne() + 1; j < 8; ++j) {
                if (conditionColonneEM(plateau, mouvements, j)) break;
            }
        }
        // Sens vers la gauche
        if (!(getColonne() - 1 < 0)) {
            for (int j = getColonne() - 1; j >= 0; --j) {
                if (conditionColonneEM(plateau, mouvements, j)) break;
            }
        }

        return mouvements;
    }

    /**
     * Permet de vérifier si la colonne est valide pour le mouvement de la reine
     * @author Lacombe Dorian
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
     * Permet de vérifier si la ligne est valide pour le mouvement de la reine
     * @author Lacombe Dorian
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

    /**
     * Permet de vérifier si la colonne est valide pour le mouvement de la reine
     * @author Lacombe Dorian
     * @param plateau Plateau de jeu
     * @param mouvements Listes des mouvements possibles
     * @param j Colonne
     * @return Vrai si la colonne est valide, faux sinon
     */
    private boolean conditionColonneEM(Plateau plateau, ArrayList<int[]> mouvements, int j) {
        if (plateau.getTableau().get(getLigne()).get(j) == null || (plateau.getTableau().get(getLigne()).get(j) instanceof Roi && plateau.getTableau().get(getLigne()).get(j).estBlanc() != estBlanc())) {
            mouvements.add(new int[]{getLigne(), j});
        } else {
            mouvements.add(new int[]{getLigne(), j});
            return true;
        }
        return false;
    }

    /**
     * Permet de vérifier si la ligne est valide pour le mouvement de la reine
     * @author Lacombe Dorian
     * @param plateau Plateau de jeu
     * @param mouvements Listes des mouvements possibles
     * @param j Ligne
     * @return Vrai si la ligne est valide, faux sinon
     */
    private boolean conditionLigneEM(Plateau plateau, ArrayList<int[]> mouvements, int j) {
        if (plateau.getTableau().get(j).get(getColonne()) == null || (plateau.getTableau().get(j).get(getColonne()) instanceof Roi && plateau.getTableau().get(j).get(getColonne()).estBlanc() != estBlanc())) {
            mouvements.add(new int[]{j, getColonne()});
        } else {
            mouvements.add(new int[]{j, getColonne()});
            return true;
        }
        return false;
    }

    public ArrayList<int[]> getPathToKing(Plateau plateau) {
        ArrayList<int[]> mouvements = new ArrayList<>();

        mouvements.add(new int[]{getLigne(), getColonne()});
        for(int i = 1; i < 8; i++) {
            if(getLigne() + i > 7 || getColonne() + i > 7) break;
            if(plateau.getTableau().get(getLigne()+i).get(getColonne()+i) == null) {
                mouvements.add(new int[]{getLigne() + i, getColonne() + i});
            } else {
                mouvements.add(new int[]{getLigne() + i, getColonne() + i});
                if(plateau.getTableau().get(getLigne()+i).get(getColonne()+i) instanceof Roi && plateau.getTableau().get(getLigne()+i).get(getColonne()+i).estBlanc() != estBlanc()) return mouvements;
                break;
            }
        }

        mouvements.clear();

        mouvements.add(new int[]{getLigne(), getColonne()});
        for(int i = 1; i < 8; i++) {
            if(getLigne() - i < 0 || getColonne() + i > 7) break;
            if(plateau.getTableau().get(getLigne()-i).get(getColonne()+i) == null) {
                mouvements.add(new int[]{getLigne()-i,getColonne()+i});
            } else {
                mouvements.add(new int[]{getLigne()-i,getColonne()+i});
                if(plateau.getTableau().get(getLigne()-i).get(getColonne()+i) instanceof Roi && plateau.getTableau().get(getLigne()-i).get(getColonne()+i).estBlanc() != estBlanc()) return mouvements;
                break;
            }
        }

        mouvements.clear();

        mouvements.add(new int[]{getLigne(), getColonne()});
        for(int i = 1; i < 8; i++) {
            if(getLigne() - i < 0 || getColonne() - i < 0) break;
            if(plateau.getTableau().get(getLigne()-i).get(getColonne()-i) == null) {
                mouvements.add(new int[]{getLigne()-i,getColonne()-i});
            } else {
                mouvements.add(new int[]{getLigne()-i,getColonne()-i});
                if(plateau.getTableau().get(getLigne()-i).get(getColonne()-i) instanceof Roi && plateau.getTableau().get(getLigne()-i).get(getColonne()-i).estBlanc() != estBlanc()) return mouvements;
                break;
            }
        }

        mouvements.clear();

        mouvements.add(new int[]{getLigne(), getColonne()});
        for(int i = 1; i < 8; i++) {
            if(getLigne() + i > 7 || getColonne() - i < 0) break;
            if(plateau.getTableau().get(getLigne()+i).get(getColonne()-i) == null) {
                mouvements.add(new int[]{getLigne()+i,getColonne()-i});
            } else {
                mouvements.add(new int[]{getLigne()+i,getColonne()-i});
                if(plateau.getTableau().get(getLigne()+i).get(getColonne()-i) instanceof Roi && plateau.getTableau().get(getLigne()+i).get(getColonne()-i).estBlanc() != estBlanc()) return mouvements;
                break;
            }
        }

        mouvements.clear();

        mouvements.add(new int[]{getLigne(), getColonne()});
        for(int i = 1; i < 8; i++) {
            if(getLigne() + i > 7) break;
            if(plateau.getTableau().get(getLigne()+i).get(getColonne()) == null) {
                mouvements.add(new int[]{getLigne() + i, getColonne()});
            } else {
                mouvements.add(new int[]{getLigne() + i, getColonne()});
                if(plateau.getTableau().get(getLigne()+i).get(getColonne()) instanceof Roi && plateau.getTableau().get(getLigne()+i).get(getColonne()).estBlanc() != estBlanc()) return mouvements;
                break;
            }
        }

        mouvements.clear();

        mouvements.add(new int[]{getLigne(), getColonne()});
        for(int i = 1; i < 8; i++) {
            if(getLigne() - i < 0) break;
            if(plateau.getTableau().get(getLigne()-i).get(getColonne()) == null) {
                mouvements.add(new int[]{getLigne()-i,getColonne()});
            } else {
                mouvements.add(new int[]{getLigne()-i,getColonne()});
                if(plateau.getTableau().get(getLigne()-i).get(getColonne()) instanceof Roi && plateau.getTableau().get(getLigne()-i).get(getColonne()).estBlanc() != estBlanc()) return mouvements;
                break;
            }
        }

        mouvements.clear();

        mouvements.add(new int[]{getLigne(), getColonne()});
        for(int i = 1; i < 8; i++) {
            if(getColonne() - i < 0) break;
            if(plateau.getTableau().get(getLigne()).get(getColonne()-i) == null) {
                mouvements.add(new int[]{getLigne(),getColonne()-i});
            } else {
                mouvements.add(new int[]{getLigne(),getColonne()-i});
                if(plateau.getTableau().get(getLigne()).get(getColonne()-i) instanceof Roi && plateau.getTableau().get(getLigne()).get(getColonne()-i).estBlanc() != estBlanc()) return mouvements;
                break;
            }
        }

        mouvements.clear();

        mouvements.add(new int[]{getLigne(), getColonne()});
        for(int i = 1; i < 8; i++) {
            if(getColonne() + i > 7) break;
            if(plateau.getTableau().get(getLigne()).get(getColonne()+i) == null) {
                mouvements.add(new int[]{getLigne(),getColonne()+i});
            } else {
                mouvements.add(new int[]{getLigne(),getColonne()+i});
                if(plateau.getTableau().get(getLigne()).get(getColonne()+i) instanceof Roi && plateau.getTableau().get(getLigne()).get(getColonne()+i).estBlanc() != estBlanc()) return mouvements;
                break;
            }
        }

        return null;
    }

    /**
     * Permet d'obtenir l'image qui correspond à la pièce Reine
     * @author Valente Hugo
     * @return L'image qui correspond à la pièce Reine
     */
    @Override
    public String getImage() {
        return estBlanc() ? "file:src/main/resources/pieces/reineBlanc.png" : "file:src/main/resources/pieces/reineNoir.png";
    }

    /**
     * Permet d'obtenir le nom de la pièce Reine
     * @author Valente Hugo
     * @return Le nom de la pièce Reine
     */
    @Override
    public String toString() {
        return "Reine[" + super.toString() + "]";
    }

}
