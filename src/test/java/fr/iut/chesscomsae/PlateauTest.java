package fr.iut.chesscomsae;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

class PlateauTest {

    @Test
    public void testTableau() {
        Plateau plateau = new Plateau();
        System.out.println(plateau.getTableau());
    }
    
}