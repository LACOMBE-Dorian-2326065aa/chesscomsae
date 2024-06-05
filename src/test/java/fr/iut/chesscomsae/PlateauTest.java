package fr.iut.chesscomsae;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;

class PlateauTest {
    @Test
    public void testTableau() {
        Joueur joueurNoir = new Joueur("Valente", "Hugo", false);
        Joueur joueurBlanc = new Joueur("Lacombe", "Dorian", true);
        Plateau plateau = new Plateau(joueurBlanc, joueurNoir);
        plateau.init();
        System.out.println("plateau de jeu : " + plateau.getTableau() + "\n");
        System.out.println("pièces noires : " + plateau.piecesNoires());
        System.out.println("nombre de pièces noires : " + plateau.piecesNoires().size() + "\n");
        System.out.println("pièces blanches : " + plateau.piecesBlanches());
        System.out.println("nombre de pièces blanches : " + plateau.piecesBlanches().size() + "\n");
        plateau.mouvement(plateau.getTableau().get(6).get(0), 0, 0);
        System.out.println("pièces noires : " + plateau.piecesNoires());
        System.out.println("nombre de pièces noires : " + plateau.piecesNoires().size() + "\n");
        System.out.println("pièces blanches : " + plateau.piecesBlanches());
        System.out.println("nombre de pièces blanches : " + plateau.piecesBlanches().size() + "\n");

        ArrayList<int[]> list = plateau.coordonnees(plateau.piecesBlanches());
        System.out.println("coordonnées possibles des pièces blanches présentes sur le plateau : " + list);
        System.out.println(plateau.echecEtMat(true));

        System.out.println(plateau.con());
    }
    
}