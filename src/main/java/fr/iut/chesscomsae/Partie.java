package fr.iut.chesscomsae;

public class Partie {

    private final Joueur joueurBlanc;
    private final Joueur joueurNoir;
    private final Joueur gagnant;

    public Partie(Joueur joueurBlanc, Joueur joueurNoir, Joueur gagnant) {
        this.joueurBlanc = joueurBlanc;
        this.joueurNoir = joueurNoir;
        this.gagnant = gagnant;
    }

    public Joueur getJoueurBlanc() {
        return joueurBlanc;
    }

    public Joueur getJoueurNoir() {
        return joueurNoir;
    }

    public Joueur getGagnant() {
        return gagnant;
    }

}
