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
                    tableau.get(i).set(j, new Tour(i, j, false, joueurNoir)); // Tours noires
                }
                else if (i==7 && (j==0 || j==7)) {
                    tableau.get(i).set(j, new Tour(i, j, true, joueurBlanc)); // Tours blanches
                }
                // Création de nouveaux cavaliers aux cases correspondantes
                else if (i==0 && (j==1 || j==6)) {
                    tableau.get(i).set(j, new Cavalier(i, j, false, joueurNoir)); // Cavaliers noirs
                }
                else if (i==7 && (j==1 || j==6)) {
                    tableau.get(i).set(j, new Cavalier(i, j, true, joueurBlanc)); // Cavaliers blancs
                }
                // Création de nouveaux fous aux cases correspondantes
                else if (i==0 && (j==2 || j==5)) {
                    tableau.get(i).set(j, new Fou(i, j, false, joueurNoir)); // Fous noirs
                }
                else if (i==7 && (j==2 || j==5)) {
                    tableau.get(i).set(j, new Fou(i, j, true, joueurBlanc)); // Fous blancs
                }
                // Création de nouvelles reines aux cases correspondantes
                else if (i==0 && j==3) {
                    tableau.get(i).set(j, new Reine(i, j, false, joueurNoir)); // Reine noire
                }
                else if (i==7 && j==3) {
                    tableau.get(i).set(j, new Reine(i, j, true, joueurBlanc)); // Reine blanche
                }
                // Création de nouveaux rois aux cases correspondantes
                else if (i==0 && j==4) {
                    tableau.get(i).set(j, new Roi(i, j, false, joueurNoir)); // Roi noir
                }
                else if (i==7 && j==4) {
                    tableau.get(i).set(j, new Roi(i, j, true, joueurBlanc)); // Roi blanc
                }
                // Création de nouveaux pions aux cases correspondantes
                else if (i==1) {
                    tableau.get(i).set(j, new Pion(i, j, false, joueurNoir)); // Pions noirs
                }
                else if (i==6) {
                    tableau.get(i).set(j, new Pion(i, j, true, joueurBlanc)); // Pions blancs
                }
            }
        }
    }

    /**
     * @author Quentin Fournier
     * getTableau, affiche les éléments, pièces, présentes sur le plateau
     */
    public ArrayList<ArrayList<Piece>> getTableau() {
        return tableau;
    }

}
