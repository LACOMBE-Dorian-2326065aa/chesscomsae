package fr.iut.chesscomsae.piece;

import fr.iut.chesscomsae.Joueur;

public abstract class Piece {

    private final int row;
    private final int column;
    private final boolean isWhite;
    private final Joueur joueur;

    /**
     * @author Hugo Valente
     * @param row Ligne de la pièce
     * @param column Colonne de la pièce
     * @param isWhite
     * description Constructeur de la classe Piece
     */
    public Piece(int row, int column, boolean isWhite, Joueur joueur) {
        this.row = row;
        this.column = column;
        this.isWhite = isWhite;
        this.joueur = joueur;
    }

    /**
     * @author Hugo Valente
     * @return La colonne de la pièce
     */
    public int getColumn() {
        return row;
    }

    /**
     * @author Hugo Valente
     * @return La ligne de la pièce
     */
    public int getRow() {
        return column;
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
     * @param row Line de la pièce
     * @param column Colonnes de la pièce
     * @return Vrai si le déplacement est légal, faux sinon
     */
    public abstract boolean isMoveLegal(int row, int column);

    /**
     * @author Hugo Valente
     * @return Le joueur de la pièce
     */
    public Joueur getJoueur() {
        return joueur;
    }
}
