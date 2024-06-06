package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;
import fr.iut.chesscomsae.Plateau;

import java.util.ArrayList;

public class Fou extends Piece {

    /**
     * Constructeur de la classe Fou
     * @author Valente Hugo
     * @param ligne Ligne de la pièce
     * @param colonne Colonne de la pièce
     * @param joueur Joueur de la pièce
     */
    public Fou(int ligne, int colonne, Joueur joueur) {
        super(ligne, colonne, joueur);
    }

    /**
     * Permet de récupérer les mouvements possibles de la pièce Fou sur le plateau
     * @author Lacombe Dorian
     * @author Valente Hugo
     * @param plateau Plateau de jeu
     * @return Liste des mouvements possibles de la pièce Fou
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

        mouvements = plateau.filtreEchec(mouvements, estBlanc());


        return plateau.canPieceMove(this, estBlanc()) ? mouvements : new ArrayList<int[]>();
        //return mouvements;
    }

    /**
     * Permet de récupérer les mouvements possibles de la pièce Fou sur le plateau, y compris les protections d'alliés
     * @author Lacombe Dorian
     * @author Valente Hugo
     * @param plateau Plateau de jeu
     * @return Liste des mouvements possibles de la pièce Fou
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

        return mouvements;
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

        return null;
    }

    /**
     * Permet de récupérer le nom de la pièce Fou
     * @author Valente Hugo
     * @return Nom de la pièce Fou
     */
    @Override
    public String getImage() {
        return estBlanc() ? "file:src/main/resources/pieces/fouBlanc.png" : "file:src/main/resources/pieces/fouNoir.png";
    }

    /**
     * Permet de récupérer le nom de la pièce Fou
     * @author Valente Hugo
     * @return Nom de la pièce Fou
     */
    @Override
    public String toString() {
        return "Fou[" + super.toString() + "]";
    }

}
