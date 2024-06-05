package fr.iut.chesscomsae;

import fr.iut.chesscomsae.gestionnairelog.ManagerFichier;
import fr.iut.chesscomsae.gestionnairelog.ManagerJoueur;
import org.junit.jupiter.api.Test;

public class ManagerTest {

    @Test
    public void testEcrireFichier() {
        ManagerJoueur manager = new ManagerJoueur();
        manager.ecrireFichier("test.json", "test");
        manager.ajouterJoueur(new Joueur("Prenom", "Nom", false));
        manager.miseAJourJoueurs();
    }

    @Test
    public void testLireFichier() {
        ManagerJoueur manager = new ManagerJoueur();
        manager.chargerJoueurs();
    }

    @Test
    public void testAjouterJoueur() {
        ManagerJoueur manager = new ManagerJoueur();
        manager.chargerJoueurs();
        manager.ajouterJoueur(new Joueur("Prenom50", "Nom", false));
        manager.miseAJourJoueurs();
        manager.afficheFichierJoueurs();
    }

    @Test
    public void suppressionJoueur() {
        ManagerJoueur manager = new ManagerJoueur();
        manager.chargerJoueurs();
        manager.effacerJoueurs();
        manager.miseAJourJoueurs();
    }

}
