package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;
import fr.iut.chesscomsae.Plateau;

import java.util.ArrayList;

public class Pion extends Piece {

    /**
     * Attribut de la classe Pion
     */
    private boolean premierCoup;

    /**
     * Constructeur de la classe Pion
     * @author Turmo Baptiste
     * @author Valente Hugo
     * @param ligne Ligne de la pièce
     * @param colonne Colonne de la pièce
     * @param joueur Joueur de la pièce
     */
    public Pion(int ligne, int colonne, Joueur joueur) {
        super(ligne, colonne, joueur);
        premierCoup = true;
    }

    /**
     * Permet de récupérer les mouvements possibles du pion sur le plateau de jeu
     * @author Turmo Baptiste
     * @author Valente Hugo
     * @param plateau Plateau de jeu
     * @return Liste des mouvements possibles du pion
     */
    @Override
    public ArrayList<int[]> mouvementsPossibles(Plateau plateau) {

        ArrayList<int[]> mouvements = new ArrayList<>();
        ArrayList<ArrayList<Piece>> tableau = plateau.getTableau();

        int deplacement = 1;
        // Regarde s'il s'agit du premier coup du pion
        if (premierCoup) {
            // Si oui, le pion peut avancer de 2 cases
            deplacement = 2;
        }

        if (estBlanc()) {
            // Ajout des mouvements possibles en avant pour le pion blanc
            for (int i = 1; i <= deplacement; ++i) {
                if (getLigne() - i >= 0 && tableau.get(getLigne()-i).get(getColonne()) == null) {
                    mouvements.add(new int[]{getLigne() - i, getColonne()});
                }else {
                    break;
                }
            }

            // Ajout des prises en diagonale lorsqu'elles sont possibles pour le pion blanc
            if (getColonne() - 1 >= 0 && getLigne() - 1 >= 0 && tableau.get(getLigne() - 1).get(getColonne() - 1) != null && tableau.get(getLigne() - 1).get(getColonne() - 1).estBlanc() != estBlanc()) mouvements.add(new int[]{getLigne()-1, getColonne()-1});
            if (getColonne() + 1 <= 7 && getLigne() - 1 >= 0 && tableau.get(getLigne() - 1).get(getColonne() + 1) != null && tableau.get(getLigne() - 1).get(getColonne() + 1).estBlanc() != estBlanc()) mouvements.add(new int[]{getLigne()-1, getColonne()+1});

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
            if (getColonne() - 1 >= 0 && getLigne() + 1 < 8 && tableau.get(getLigne()+1).get(getColonne()-1) != null && tableau.get(getLigne() + 1).get(getColonne()-1).estBlanc() != estBlanc()) mouvements.add(new int[]{getLigne()+1, getColonne()-1});
            if (getColonne() + 1 <= 7 && getLigne() + 1 < 8 && tableau.get(getLigne()+1).get(getColonne()+1) != null && tableau.get(getLigne() + 1).get(getColonne()+1).estBlanc() != estBlanc()) mouvements.add(new int[]{getLigne()+1, getColonne()+1});

        }

        mouvements = plateau.filtreEchec(mouvements, estBlanc());

        return mouvements;
    }

    /**
     * Permet de récupérer les mouvements possibles du pion sur le plateau de jeu, y compris les protections d'alliés
     * @author Turmo Baptiste
     * @author Valente Hugo
     * @param plateau Plateau de jeu
     * @return Liste des mouvements possibles du pion
     */
    @Override
    public ArrayList<int[]> mouvementsPossiblesEchecEtMat(Plateau plateau) {

        ArrayList<int[]> mouvements = new ArrayList<>();
        ArrayList<ArrayList<Piece>> tableau = plateau.getTableau();

        int deplacement = 1;
        // Regarde s'il s'agit du premier coup du pion
        if (premierCoup) {
            // Si oui, le pion peut avancer de 2 cases
            deplacement = 2;
        }

        if (estBlanc()) {
            // Ajout des mouvements possibles en avant pour le pion blanc
            for (int i = 1; i <= deplacement; ++i) {
                if (getLigne() - i >= 0 && tableau.get(getLigne()-i).get(getColonne()) == null) {
                    mouvements.add(new int[]{getLigne() - i, getColonne()});
                }else {
                    break;
                }
            }

            // Ajout des prises en diagonale lorsqu'elles sont possibles pour le pion blanc
            if (getColonne() - 1 >= 0 && getLigne() - 1 >= 0 && tableau.get(getLigne() - 1).get(getColonne() - 1) != null) mouvements.add(new int[]{getLigne()-1, getColonne()-1});
            if (getColonne() + 1 <= 7 && getLigne() - 1 >= 0 && tableau.get(getLigne() - 1).get(getColonne() + 1) != null) mouvements.add(new int[]{getLigne()-1, getColonne()+1});

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
            if (getColonne() - 1 >= 0 && getLigne() + 1 < 8) mouvements.add(new int[]{getLigne()+1, getColonne()-1});
            if (getColonne() + 1 <= 7 && getLigne() + 1 < 8) mouvements.add(new int[]{getLigne()+1, getColonne()+1});

        }
        return mouvements;
    }

    public ArrayList<int[]> getPathToKing(Plateau plateau) {
        ArrayList<int[]> path = new ArrayList<>();
        path.add(new int[]{getLigne(), getColonne()});
        return path;
    }

    /**
     * Permet de savoir si le pion est en position de promotion
     * @author Lacombe Dorian
     */
    public void setPremierCoup(boolean premierCoup) {
        this.premierCoup = premierCoup;
    }

    /**
     * Permet d'obtenir l'image qui correspond au pion
     * @author Valente Hugo
     * @return L'image qui correspond au pion
     */
    @Override
    public String getImage() {
        return estBlanc() ? "file:src/main/resources/pieces/pionBlanc.png" : "file:src/main/resources/pieces/pionNoir.png";
    }

    /**
     * Permet de récupérer le nom de la pièce Pion
     * @author Valente Hugo
     * @return Nom de la pièce Pion
     */
    @Override
    public String toString() {
        return "Pion[" + super.toString() + "]";
    }

}