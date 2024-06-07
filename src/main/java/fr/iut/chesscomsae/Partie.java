package fr.iut.chesscomsae;

public class Partie {

    private final Joueur joueurGagnant;
    private final Joueur joueurPerdant;
    private final boolean PAT;

    public Partie(Joueur joueurGagnant, Joueur joueurPerdant, boolean PAT) {
        this.joueurGagnant = joueurGagnant;
        this.joueurPerdant = joueurPerdant;
        this.PAT = PAT;
    }

    public Joueur getJoueurGagnant() {
        return joueurGagnant;
    }

    public Joueur getJoueurPerdant() {
        return joueurPerdant;
    }
    public boolean isPAT() {
        return PAT;
    }


}
