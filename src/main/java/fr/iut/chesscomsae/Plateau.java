package fr.iut.chesscomsae;

import fr.iut.chesscomsae.piece.*;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Plateau {

    private ArrayList<ArrayList<Piece>> tableau;
    private Joueur joueurBlanc;
    private Joueur joueurNoir;
    public Roi roiBlanc;
    public Roi roiNoir;

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
     * Init, permet d'initialiser le plateau pour le début de partie, place les pions des différents joueurs au bon endroit
     * @author Quentin Fournier
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
                    tableau.get(i).set(j, roiNoir = new Roi(i, j, joueurNoir)); // Roi noir
                }
                else if (i==7 && j==4) {
                    tableau.get(i).set(j, roiBlanc = new Roi(i, j, joueurBlanc)); // Roi blanc
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
    public int mouvement (Piece piece, int x, int y) {
        //if (!piece.isMoveLegal(x, y)) return; // Si le mouvement est illégal, alors rien ne se passe
        boolean isMoveLegal = false;
        ArrayList<int[]> moves = piece.mouvementsPossibles(this);
        for(int[] move : moves) {
            if(x == move[0] && y == move[1]) {
                isMoveLegal = true;
            }
        }

        if (tableau.get(x).get(y) != null && tableau.get(x).get(y).estBlanc() == piece.estBlanc()) return 1; // Si il y a une pièce de meme couleur dans la case visée, on ne fait rien et on renvoie 0
        else if(isMoveLegal) {
            tableau.get(piece.getLigne()).set(piece.getColonne(), null); // On met a null l'ancienne position de la pièce dans le tableau
            if(piece.estBlanc() && piece instanceof Pion && x == 0)
                piece = new Reine(x, y, piece.getJoueur());
            else if(!piece.estBlanc() && piece instanceof Pion && x == 7)
                piece = new Reine(x, y, piece.getJoueur());
            tableau.get(x).set(y, piece); // On met la pièce a sa nouvelle position, supprimant alors celle qui était ici avant
            piece.setLigne(x); // on met a jour la coordonnée x de la pièce
            piece.setColonne(y); // on met a jour la coordonnée y de la pièce
            return 2;
        }
        return 0;
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

    public int[] getCoordonnees (Piece piece) {
        int[] coordonnees = new int[]{piece.getLigne(),piece.getColonne()};
        return coordonnees;
    }

    private boolean comparerCoordonnees(int[] coord, int[] coord2) {
        return coord[0] == coord2[0] && coord[1] ==coord2[1];
    }

    // pour piece : chaque pièce adverse
    // pour i : chaque déplacement possible de cette pièce
    // pour j : chaque déplacement possible du roi
    // Si comparerCoordonnees(i, j) -> return false (le roi peut bouger)

    public boolean echecEtMat (boolean isWhitePlaying) {
        //System.out.println("la con de smr");
        if (isWhitePlaying == true) {
            if (!piecesBlanches().contains(roiBlanc)) return true; // Si le roi blanc n'existe plus, alors il y a échec et mat
            else {
                for (Piece piece : piecesNoires()) {
                    //System.out.println("pièces noires : " + piece);
                    for (int[] i : piece.mouvementsPossibles(this)) {
                        //System.out.println("piece : " + piece + " " + piece.mouvementsPossibles(this));
                        for (int[] j : roiBlanc.mouvementsPossibles(this)) {
                            //System.out.println("roi blanc : " + getCoordonnees(roiBlanc));
                            //System.out.println("déplacements possibles roi blanc : " + roiBlanc.mouvementsPossibles(this));
                            //System.out.println(j);
                            //System.out.println("comp : " + comparerCoordonnees(i,j));
                            if (!comparerCoordonnees(i, j)) {
                                return false;
                            }
                        }
                    }
                }
                return true;
            }
        }
        else {
            if (!piecesNoires().contains(roiNoir)) return true; // Si le roi noir n'existe plus, alors il y a échec et mat
            else {
                for (Piece piece2 : piecesBlanches()) {
                    //System.out.println("pièces noires : " + piece2);
                    for (int[] k : piece2.mouvementsPossibles(this)) {
                        //System.out.println("piece : " + piece2 + " " + piece2.mouvementsPossibles(this));
                        for (int[] l : roiNoir.mouvementsPossibles(this)) {
                            //System.out.println(l);
                            //System.out.println("comp : " + comparerCoordonnees(k,l));
                            if (!comparerCoordonnees(k, l)) {
                                return false;
                            }
                        }
                    }
                }
                return true;
            }
        }
    }

    public ArrayList<int[]> getPathsToKing(boolean isWhitePlaying) {
        ArrayList<Piece> pieces = isWhitePlaying ? piecesNoires() : piecesBlanches();
        ArrayList<int[]> movesKiller = new ArrayList<>();
        for (Piece pieceKiller : pieces) {
            if(pieceKiller.getPathToKing(this) == null || pieceKiller.getPathToKing(this).size() <= 1) continue;
            for (int[] moveKill : pieceKiller.getPathToKing(this))
            {
                movesKiller.add(moveKill);
            }
        }

        return movesKiller;
    }

    public ArrayList<int[]> filtreEchec(ArrayList<int[]> movesPossibles, boolean isWhitePlaying) {
        ArrayList<int[]> movesKiller = getPathsToKing(isWhitePlaying);
        ArrayList<int[]> toFinal = new ArrayList<>();
        for (int[] move : movesPossibles) {
            for (int[] moveKill : movesKiller) {
                if(comparerCoordonnees(move, moveKill)) toFinal.add(move);
            }
        }
        if(toFinal.size() > 0 || movesKiller.size() > 0) movesPossibles = toFinal;
        return movesPossibles;
    }

    public ArrayList<int[]> filtreEchecRoi(ArrayList<int[]> movesPossibles, boolean isWhitePlaying) {
        ArrayList<Piece> pieces = isWhitePlaying ? piecesNoires() : piecesBlanches();
        ArrayList<int[]> movesRoi = movesPossibles;

        ArrayList<int[]> toRemove = new ArrayList<>();

        for (Piece piece : pieces) {
            ArrayList<int[]> movesPiece = new ArrayList<>();
            movesPiece.addAll(piece.mouvementsPossiblesEchecEtMat(this));
            for (int[] movePiece : movesPiece) {
                for (int[] moveRoi : movesRoi) {
                    if (comparerCoordonnees(movePiece, moveRoi)) toRemove.add(moveRoi);
                }
            }
        }
        movesRoi.removeAll(toRemove);

        return movesRoi;
    }

    public boolean testEchecEtMat(boolean isWhitePlaying) {
        ArrayList<Piece> pieces = isWhitePlaying ? piecesNoires() : piecesBlanches();
        ArrayList<Piece> pieces2 = isWhitePlaying ? piecesBlanches() : piecesNoires();
        ArrayList<int[]> movesRoi = new ArrayList<>();
        ArrayList<int[]> movesRoiSave = new ArrayList<>();
        int[] roiPos;
        if(!isWhitePlaying) {
            movesRoi.addAll(roiNoir.mouvementsPossibles(this));
            movesRoiSave.addAll(roiNoir.mouvementsPossibles(this));
            roiPos = new int[]{roiNoir.getLigne(), roiNoir.getColonne()};
        }else{
            movesRoi.addAll(roiBlanc.mouvementsPossibles(this));
            movesRoiSave.addAll(roiBlanc.mouvementsPossibles(this));
            roiPos = new int[]{roiBlanc.getLigne(), roiBlanc.getColonne()};
        }
        movesRoi.add(roiPos);
        movesRoiSave.add(roiPos);

        ArrayList<Piece> killer = new ArrayList<>();
        ArrayList<int[]> toRemove = new ArrayList<>();

        for (Piece piece : pieces) {
            ArrayList<int[]> movesPiece = new ArrayList<>();
            movesPiece.addAll(piece.mouvementsPossiblesEchecEtMat(this));
            for (int[] movePiece : movesPiece) {
                for (int[] moveRoi : movesRoi) {
                    if (comparerCoordonnees(movePiece, moveRoi)) toRemove.add(moveRoi);
                    if (comparerCoordonnees(movePiece, roiPos)) killer.add(piece);
                }
            }
        }
        movesRoi.removeAll(toRemove);

        ArrayList<Piece> killToRemove = new ArrayList<>();

        for (Piece piece : pieces2) {
            if(piece instanceof Roi) continue;
            ArrayList<int[]> movesPiece = new ArrayList<>();
            movesPiece.addAll(piece.mouvementsPossibles(this));
            for (int[] movePiece : movesPiece) {
                for (Piece pieceKiller : killer) {
                    if(pieceKiller.getPathToKing(this) == null) continue;
                    for (int[] moveKill : pieceKiller.getPathToKing(this)) {
                        if (comparerCoordonnees(movePiece, moveKill)) killToRemove.add(pieceKiller);
                    }
                }
            }
        }

        // RESTE A GERER LES MOUVEMENTS POSSIBLES CHIANTS DU PION QUI GÊNENT
        // + DETECTEUR D'ECHEC TOUT COURT POUR EMPECHER DES PIECES DE BOUGER TOUT CA

        killer.removeAll(killToRemove);

        return movesRoi.size() == 0 && killer.size() > 0;
    }

    public boolean canPieceMove(Piece checkedPiece, boolean isWhitePlaying) {
        int nb = 0;
        ArrayList<Piece> pieces = isWhitePlaying ? piecesNoires() : piecesBlanches();
        int[] roiPos;
        if(!isWhitePlaying) {
            roiPos = new int[]{roiNoir.getLigne(), roiNoir.getColonne()};
        }else{
            roiPos = new int[]{roiBlanc.getLigne(), roiBlanc.getColonne()};
        }
        for(Piece piece : pieces) {
            ArrayList<int[]> movesPiece = new ArrayList<>();
            movesPiece.addAll(piece.mouvementsPossiblesEchecEtMat(this));
            for(int[] move : movesPiece) {
                if(comparerCoordonnees(move, roiPos)) {
                    nb++;
                }
            }
        }
        tableau.get(checkedPiece.getLigne()).set(checkedPiece.getColonne(), null);

        int nb2 = 0;
        for(Piece piece : pieces) {
            ArrayList<int[]> movesPiece = new ArrayList<>();
            movesPiece.addAll(piece.mouvementsPossiblesEchecEtMat(this));
            for(int[] move : movesPiece) {
                if(comparerCoordonnees(move, roiPos)) {
                    nb2++;
                }
            }
        }

        tableau.get(checkedPiece.getLigne()).set(checkedPiece.getColonne(), checkedPiece);
        return nb == nb2;

        // FAUT ENCORE CORRIGER LE PROBLEME DU PION QUI NE FAIT PAS D'ECHEC :( + qui bloque devant sans raison
    }

    /**
     * @author Quentin Fournier
     * getTableau, affiche les éléments, pièces, présentes sur le plateau
     * @return le tableau (la liste des listes et de leurs éléments)
     */
    public ArrayList<ArrayList<Piece>> getTableau() {
        return tableau;
    }

    public void setTableau(ArrayList<ArrayList<Piece>> tableau) {
        this.tableau = tableau;
    }

}
