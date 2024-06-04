package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;
import fr.iut.chesscomsae.Plateau;

import java.util.ArrayList;

public abstract class Piece {

    /**
     * Attributs de la classe Piece
     */
    private int ligne;
    private int colonne;
    private final boolean estBlanc;
    private final Joueur joueur;
    
    /**
     * Constructeur de la classe Piece
     * @author Hugo Valente
     * @param ligne Ligne de la pièce
     * @param colonne Colonne de la pièce
     * @param joueur Joueur de la pièce
     */
    public Piece(int ligne, int colonne, Joueur joueur) {
        setLigne(ligne);
        setColonne(colonne);
        this.estBlanc = joueur.estBlanc();
        this.joueur = joueur;
    }

    /**
     * Permet d'obtenir la colonne de la pièce
     * @author Hugo Valente
     * @return La colonne de la pièce
     */
    public int getColonne() {
        return colonne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    /**
     * Permet d'obtenir la ligne de la pièce
     * @author Hugo Valente
     * @return La ligne de la pièce
     */
    public int getLigne() {
        return ligne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    /**
     * Permet de savoir si la pièce est blanche ou non
     * @author Hugo Valente
     * @return Vrai si la pièce est blanche, faux sinon
     */
    public boolean estBlanc() {
        return estBlanc;
    }

    /**
     * Permet d'obtenir l'image qui correspond à la pièce
     * @return L'image qui correspond à la pièce
     * @author Hugo Valente
     */
    public abstract String getImage();

    /**
     * Permet d'obtenir les mouvements possibles de la pièce
     * @param plateau Plateau de jeu
     * @return Les mouvements possibles de la pièce
     */
    public abstract ArrayList<int[]> mouvementsPossibles(Plateau plateau);

    /**
     * Permet d'obtenir le joueur qui correspond à la pièce
     * @author Hugo Valente
     * @return Le joueur de la pièce
     */
    public Joueur getJoueur() {
        return joueur;
    }

    /**
     * Permet d'obtenir une chaine de caractère qui représente la pièce
     * @author Hugo Valente
     * @return Une chaine de caractère qui représente la pièce
     */
    public String toString() {
        return "Piece[" +
                "ligne=" + ligne +
                ", colonne=" + colonne +
                ", estBlanc=" + estBlanc +
                ", joueur=" + joueur.toString() +
                ']';
    }

}
