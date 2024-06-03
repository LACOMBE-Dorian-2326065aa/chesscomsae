package fr.iut.chesscomsae;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

class PlateauTest {

    @Test
    public void testTableau() {
        Joueur joueurNoir = new Joueur("Valente", "Hugo");
        Joueur joueurBlanc = new Joueur("Lacombe", "Dorian");
        Plateau plateau = new Plateau(joueurBlanc, joueurNoir);
        plateau.init();
        System.out.println(plateau.getTableau());
    }
    
}