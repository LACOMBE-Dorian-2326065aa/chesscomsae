package fr.iut.chesscomsae;

import java.util.ArrayList;

public class Plateau {

    private ArrayList<ArrayList> tableau;

    public Plateau() {
        tableau = new ArrayList<>(8);
        for (int i=0; i<8; i++) {
            tableau.add(new ArrayList<>(8));
            for (int j=0; j<8; j++) {
                tableau.get(i).add(null);
            }
        }
    }
}
