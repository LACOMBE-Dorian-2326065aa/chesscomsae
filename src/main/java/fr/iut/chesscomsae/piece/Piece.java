package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;

public abstract class Piece {

    private final int x;
    private final int y;
    private final boolean isWhite;
    private final Joueur joueur;

    /**
     * @author Hugo Valente
     * @param x Ligne de la pièce
     * @param y Colonne de la pièce
     * @param isWhite
     * description Constructeur de la classe Piece
     */
    public Piece(int x, int y, boolean isWhite, Joueur joueur) {
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
        this.joueur = joueur;
    }

    /**
     * @author Hugo Valente
     * @return La colonne de la pièce
     */
    public int getColumn() {
        return x;
    }

    /**
     * @author Hugo Valente
     * @return La ligne de la pièce
     */
    public int getRow() {
        return y;
    }

    /**
     * @author Hugo Valente
     * @return Vrai si la pièce est blanche, faux sinon
     */
    public boolean isWhite() {
        return isWhite;
    }

    /**
     * @author Hugo Valente
     * @param x Line de la pièce
     * @param y Colonnes de la pièce
     * @return Vrai si le déplacement est légal, faux sinon
     */
    public abstract boolean isMoveLegal(int x, int y);

    /**
     * @author Hugo Valente
     * @return Le joueur de la pièce
     */
    public Joueur getJoueur() {
        return joueur;
    }
}
