package fr.iut.chesscomsae;

import fr.iut.chesscomsae.piece.*;

import java.util.ArrayList;

public class Plateau {

    private ArrayList<ArrayList<Piece>> tableau;
    private Joueur joueurBlanc;
    private Joueur joueurNoir;

    /**
     * @author Quentin Fournier
     * @param joueurBlanc De type joueur, c'est celui qui joue les pièces blanches
     * @param joueurNoir De type joueur, c'est celui qui joue les pièces noires
     * Constructeur Plateau, crée un plateau de 8 par 8 (ArrayList de 8 ArrayList, elles mêmes composées de 8 pièces) et le rempli d'éléments null
     */
    public Plateau(Joueur joueurBlanc, Joueur joueurNoir) {
        this.joueurNoir = joueurNoir;
        this.joueurBlanc = joueurBlanc;
        tableau = new ArrayList<>(8);
        for (int i=0; i<8; i++) {
            tableau.add(new ArrayList<>(8));
            for (int j = 0; j < 8; j++) {
                tableau.get(i).add(null);
            }
        }
    }

    /**
     * @author Quentin Fournier
     * Init, permet d'initialiser le plateau pour le début de partie, place les pions des différents joueurs au bon endroit
     */
    public void init() {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                // Création de nouvelles tours aux cases correspondantes
                if (i==0 && (j==0 || j==7)) {
                    tableau.get(i).set(j, new Tour(i, j, joueurNoir)); // Tours noires
                }
                else if (i==7 && (j==0 || j==7)) {
                    tableau.get(i).set(j, new Tour(i, j, joueurBlanc)); // Tours blanches
                }
                // Création de nouveaux cavaliers aux cases correspondantes
                else if (i==0 && (j==1 || j==6)) {
                    tableau.get(i).set(j, new Cavalier(i, j, joueurNoir)); // Cavaliers noirs
                }
                else if (i==7 && (j==1 || j==6)) {
                    tableau.get(i).set(j, new Cavalier(i, j, joueurBlanc)); // Cavaliers blancs
                }
                // Création de nouveaux fous aux cases correspondantes
                else if (i==0 && (j==2 || j==5)) {
                    tableau.get(i).set(j, new Fou(i, j, joueurNoir)); // Fous noirs
                }
                else if (i==7 && (j==2 || j==5)) {
                    tableau.get(i).set(j, new Fou(i, j, joueurBlanc)); // Fous blancs
                }
                // Création de nouvelles reines aux cases correspondantes
                else if (i==0 && j==3) {
                    tableau.get(i).set(j, new Reine(i, j, joueurNoir)); // Reine noire
                }
                else if (i==7 && j==3) {
                    tableau.get(i).set(j, new Reine(i, j, joueurBlanc)); // Reine blanche
                }
                // Création de nouveaux rois aux cases correspondantes
                else if (i==0 && j==4) {
                    tableau.get(i).set(j, new Roi(i, j, joueurNoir)); // Roi noir
                }
                else if (i==7 && j==4) {
                    tableau.get(i).set(j, new Roi(i, j, joueurBlanc)); // Roi blanc
                }
                // Création de nouveaux pions aux cases correspondantes
                else if (i==1) {
                    tableau.get(i).set(j, new Pion(i, j, joueurNoir)); // Pions noirs
                }
                else if (i==6) {
                    tableau.get(i).set(j, new Pion(i, j, joueurBlanc)); // Pions blancs
                }
            }
        }
    }

    /**
     * @author Quentin Fournier
     * @param piece De type Piece, c'est la pièce que le joueur souhaite bouger
     * @param x De type int, c'est l'index de la ligne vers laquelle le joueur souhaite bouger sa pièce
     * @param y De type int, c'est l'index de la colonne vers laquelle le joueur souhaite bouger sa pièce
     * mouvement, vérifie si le mouvement est légal, si c'est le cas, il y a une autre condition.
     * Si il y a une pièce de meme couleur a l'endroit visé, rien ne se passe; sinon, on met a l'ancienne position de la pièce un élément null
     * puis met dans la case aux coordonnées voulues, la pièce. Enfin, met a jour les coordonnées de la pièce.
     */
    public void mouvement (Piece piece, int x, int y) {
        //if (!piece.isMoveLegal(x, y)) return; // Si le mouvement est illégal, alors rien ne se passe
        if (tableau.get(x).get(y) != null && tableau.get(x).get(y).estBlanc() == piece.estBlanc()) return; // Si il y a une pièce de meme couleur dans la case visée, on ne fait rien
        else {
            tableau.get(piece.getLigne()).set(piece.getColonne(), null); // On met a null l'ancienne position de la pièce dans le tableau
            tableau.get(x).set(y, piece); // On met la pièce a sa nouvelle position, supprimant alors celle qui était ici avant
            piece.setLigne(x); // on met a jour la coordonnée x de la pièce
            piece.setColonne(y); // on met a jour la coordonnée y de la pièce
        }
    }

    /**
     * @author Quentin Fournier
     * piecesBlanches, crée une liste contenant les pièces blanches présentes sur le plateau
     * @return la liste piecesBlanches contenant des pieces de type Piece dont isWhite = true, étant présentes sur le plateau de jeu
     */
    public ArrayList<Piece> piecesBlanches () {
        ArrayList<Piece> piecesBlanches = new ArrayList<Piece>();
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j)  {
                if (tableau.get(i).get(j) == null) continue;
                else if (tableau.get(i).get(j).estBlanc()) {
                    piecesBlanches.add(tableau.get(i).get(j));
                }
            }
        }
        return piecesBlanches;
    }

    /**
     * @author Quentin Fournier
     * piecesNoires, crée une liste contenant les pièces noires présentes sur le plateau
     * @return la liste piecesNoires contenant des pieces de type Piece dont isWhite = false, étant présentes sur le plateau de jeu
     */
    public ArrayList<Piece> piecesNoires () {
        ArrayList<Piece> piecesNoires = new ArrayList<Piece>();
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j)  {
                if (tableau.get(i).get(j) == null) continue;
                else if (!tableau.get(i).get(j).estBlanc()) {
                    piecesNoires.add(tableau.get(i).get(j));
                }
            }
        }
        return piecesNoires;
    }

    /**
     * @author Quentin Fournier
     * @param listePiece, une ArrayList composée de pieces de type Piece
     * piecesBlanches, crée une liste contenant les pièces blanches présentes sur le plateau
     * @return la liste piecesBlanches contenant des pieces de type Piece étant présentes sur le plateau de jeu
     */
    public ArrayList<int[]> coordonnees (ArrayList<Piece> listePiece) {
        ArrayList<int[]> coordonnees = new ArrayList<int[]>();
        for (Piece piece : listePiece) {
            for (int i = 0; i < piece.mouvementsPossibles(this).size(); ++i) {
                if (coordonnees.contains(piece.mouvementsPossibles(this).get(i))) continue;
                else {
                    coordonnees.add(piece.mouvementsPossibles(this).get(i));
                }
            }
        }
        return coordonnees;
    }

/*
    // ArrayList<int> mouvementsPossibles(Plateau plateau)
    public boolean echecEtMat () {
        for (i : roi.mouvementsPossibles(tableau)) {

        }
    }

    si une des coordonnées possibles du roi blanc != de toutes les coordonnées possibles des pièces noires {
        return false;
    }
    sinon return true;

    si une des coordonnées possibles du roi noir != de toutes les coordonnées possibles des pièces blanches {
        return false;
    }
    sinon return true;
*/

    /**
     * @author Quentin Fournier
     * getTableau, affiche les éléments, pièces, présentes sur le plateau
     * @return le tableau (la liste des listes et de leurs éléments)
     */
    public ArrayList<ArrayList<Piece>> getTableau() {
        return tableau;
    }

}
