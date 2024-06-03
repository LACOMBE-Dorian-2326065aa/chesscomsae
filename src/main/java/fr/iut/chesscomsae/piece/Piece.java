package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;

public abstract class Piece {

    private final int ligne;
    private final int colonne;
    private final boolean estBlanc;
    private final Joueur joueur;

    /**
     * @author Hugo Valente
     * @param ligne Ligne de la pièce
     * @param colonne Colonne de la pièce
     * @param estBlanc
     * description Constructeur de la classe Piece
     */
    public Piece(int ligne, int colonne, boolean estBlanc, Joueur joueur) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.estBlanc = estBlanc;
        this.joueur = joueur;
    }

    /**
     * @author Hugo Valente
     * @return La colonne de la pièce
     */
    public int getColonne() {
        return ligne;
    }

    /**
     * @author Hugo Valente
     * @return La ligne de la pièce
     */
    public int getLigne() {
        return colonne;
    }

    /**
     * @author Hugo Valente
     * @return Vrai si la pièce est blanche, faux sinon
     */
    public boolean estBlanc() {
        return estBlanc;
    }

    /**
     * @author Hugo Valente
     * @param ligne Line de la pièce
     * @param colonne Colonnes de la pièce
     * @return Vrai si le déplacement est légal, faux sinon
     */
    public abstract boolean isMoveLegal(int ligne, int colonne);

    /**
     * @author Hugo Valente
     * @return Le joueur de la pièce
     */
    public Joueur getJoueur() {
        return joueur;
    }
}
