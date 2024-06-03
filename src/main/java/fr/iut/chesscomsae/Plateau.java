package fr.iut.chesscomsae;

import fr.iut.chesscomsae.piece.Piece;

import java.util.ArrayList;

public class Plateau {

    private ArrayList<ArrayList<Piece>> tableau;

    public Plateau() {
        tableau = new ArrayList<>(8);
        for (int i=0; i<8; i++) {
            tableau.add(new ArrayList<>(8));
            for (int j=0; j<8; j++) {
                tableau.get(i).add(null);
            }
        }
    }

    public ArrayList<ArrayList<Piece>> getTableau() {
        return tableau;
    }

}
