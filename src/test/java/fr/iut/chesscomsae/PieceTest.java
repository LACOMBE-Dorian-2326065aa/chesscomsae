package fr.iut.chesscomsae;

import fr.iut.chesscomsae.piece.Cavalier;
import org.junit.jupiter.api.Test;

public class PieceTest {
    @Test
    public void testGetImage() {
        Joueur joueur = new Joueur("Prenom", "Nom", false);
        Cavalier cavalier = new Cavalier(0, 0, false, joueur);
        assert cavalier.getImage().equals("img/piece/cavalierNoir.png");
    }

    @Test
    public void testToString() {
        Joueur joueur = new Joueur("Prenom", "Nom", false);
        Cavalier cavalier = new Cavalier(0, 0, false, joueur);
        assert cavalier.toString().equals("Cavalier[Piece[ligne=0, colonne=0, estBlanc=false, joueur=Joueur[nom='Prenom', prenom='Nom', nombrePartiesJouees=0, nombrePartiesGagnees=0, estBlanc=false]]]");
    }

}
