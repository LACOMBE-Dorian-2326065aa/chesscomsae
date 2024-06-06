package fr.iut.chesscomsae;

public class Partie {

    private final Joueur joueurGagnant;
    private final Joueur joueurPerdant;

    public Partie(Joueur joueurGagnant, Joueur joueurPerdant) {
        this.joueurGagnant = joueurGagnant;
        this.joueurPerdant = joueurPerdant;
    }

    public Joueur getJoueurGagnant() {
        return joueurGagnant;
    }

    public Joueur getJoueurPerdant() {
        return joueurPerdant;
    }


}
