package fr.iut.chesscomsae;

import com.google.gson.JsonObject;
import fr.iut.chesscomsae.gestionnairelog.ManagerFichier;
import fr.iut.chesscomsae.gestionnairelog.ManagerJoueur;
import fr.iut.chesscomsae.gestionnairelog.ManagerParties;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ManagerTest {

    @Test
    public void testReadFileToJson() {
        ArrayList<JsonObject> jsonObject = ManagerJoueur.fichierVersListeJsonObject("joueurs.json");

        JsonObject e = new JsonObject();

        jsonObject.add(e);
        System.out.println(jsonObject);
    }

    @Test
    public void testEcriture() {
        ManagerFichier managerFichier = new ManagerFichier();
        String chemin = "test.json";
        ArrayList<JsonObject> jsonObject = ManagerJoueur.fichierVersListeJsonObject(chemin);

        JsonObject e = new JsonObject();
        e.addProperty("nom", "test");
        e.addProperty("prenom", "test");
        e.addProperty("partiesJouees", 0);
        e.addProperty("partiesGagnees", 0);

        jsonObject.add(e);

        managerFichier.listeJsonObjectVersFichier(jsonObject, chemin);
    }

    @Test
    public void testAjouterJoueur() {
        ManagerJoueur managerJoueur = new ManagerJoueur();
        managerJoueur.ajouterJoueur(new Joueur("test", "test"));
        managerJoueur.ajouterJoueur(new Joueur("Lacombe", "Dorian"));
    }

    @Test
    public void testVictoire() {
        Joueur joueurBlanc = new Joueur("Lacombe", "Dorian", true);
        ManagerJoueur managerJoueur = new ManagerJoueur();
        managerJoueur.modifieJoueurInformation(joueurBlanc, joueurBlanc.getNombrePartiesJouees()+50, joueurBlanc.getNombrePartiesGagnees()+1);
        int valeurGagnees = joueurBlanc.getNombrePartiesGagnees();
        int valeurJouees = joueurBlanc.getNombrePartiesJouees();
        managerJoueur.modifieJoueurInformation(joueurBlanc, joueurBlanc.getNombrePartiesJouees()+50, joueurBlanc.getNombrePartiesGagnees()+5);
        assert joueurBlanc.getNombrePartiesGagnees() == valeurGagnees + 5;
        assert joueurBlanc.getNombrePartiesJouees() == valeurJouees + 50;
    }

    @Test
    public void testRecuperationJoueur() {
            System.out.println(ManagerJoueur.getJoueurs());
    }

    @Test
    public void testRecuperationJoueurBis() {
        Joueur joueurBlanc = new Joueur("Lacombe", "Dorian", true);
        ManagerJoueur.miseAJourJoueur(joueurBlanc);
        System.out.println(joueurBlanc.getNombrePartiesJouees());
    }

    @Test
    public void testRecuperationJoueurTer() {
        System.out.println(ManagerJoueur.getJoueurs());
    }

    @Test
    public void testCreationParties() {
        Joueur joueurBlanc = new Joueur("Lacombe", "Dorian", true);
        Joueur joueurNoir = new Joueur("Fournier", "Quentin", false);
        ManagerParties managerParties = new ManagerParties();
        managerParties.ajouterPartie(joueurBlanc, joueurNoir, false);
    }

    @Test
    public void testCreationPartiesBis() {
        Joueur joueurBlanc = new Joueur("Turmo", "Baptiste", true);
        Joueur joueurNoir = new Joueur("Fournier", "Quentin", false);
        ManagerParties managerParties = new ManagerParties();
        managerParties.ajouterPartie(joueurBlanc, joueurNoir, false);
    }

    @Test
    public void testLectureParties() {
        for (Partie partie : ManagerParties.getParties()) {
            System.out.println(partie.getJoueurGagnant().getNom() + " " + partie.getJoueurGagnant().getPrenom() + " " + partie.getJoueurPerdant().getNom() + " " + partie.getJoueurPerdant().getPrenom());
        }
    }

}
