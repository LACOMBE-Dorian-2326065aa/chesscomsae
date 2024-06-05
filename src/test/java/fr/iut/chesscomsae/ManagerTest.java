package fr.iut.chesscomsae;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.iut.chesscomsae.gestionnairelog.ManagerFichier;
import fr.iut.chesscomsae.gestionnairelog.ManagerJoueur;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ManagerTest {

    @Test
    public void testReadFileToJson() {
        ManagerJoueur managerJoueur = new ManagerJoueur(null, null);
        ArrayList<JsonObject> jsonObject = managerJoueur.fichierVersListeJsonObject("joueurs.json");

        JsonObject e = new JsonObject();

        jsonObject.add(e);
        System.out.println(jsonObject);
    }

    @Test
    public void testEcriture() {
        ManagerJoueur managerJoueur = new ManagerJoueur(null, null);
        ArrayList<JsonObject> jsonObject = managerJoueur.fichierVersListeJsonObject("joueurs.json");

        JsonObject e = new JsonObject();
        e.addProperty("nom", "test");
        e.addProperty("prenom", "test");
        e.addProperty("partiesJouees", 0);
        e.addProperty("partiesGagnees", 0);

        jsonObject.add(e);

        managerJoueur.listeJsonObjectVersFichier(jsonObject, "joueurs.json");
    }

    @Test
    public void testAjouterJoueur() {
        ManagerJoueur managerJoueur = new ManagerJoueur(new Joueur("test", "test"), new Joueur("test", "test"));
        managerJoueur.ajouterJoueur(new Joueur("test", "test"));
        managerJoueur.ajouterJoueur(new Joueur("Lacombe", "Dorian"));
    }

    @Test
    public void testVictoire() {
        Joueur joueurBlanc = new Joueur("Lacombe", "Dorian", true);
        Joueur joueurNoir = new Joueur("test", "test", false);
        ManagerJoueur managerJoueur = new ManagerJoueur(joueurBlanc, joueurNoir);
        managerJoueur.ajouterJoueur(joueurNoir);
        managerJoueur.ajouterJoueur(joueurBlanc);
        managerJoueur.modifieJoueurInformation(joueurBlanc, joueurBlanc.getNombrePartiesJouees()+50, 1);
        managerJoueur.modifieJoueurInformation(joueurBlanc, joueurBlanc.getNombrePartiesJouees()+50, 1);
    }

    @Test
    public void testRecuperationJoueur() {
            System.out.println(ManagerJoueur.getJoueurs());
    }

    @Test
    public void testRecuperationJoueurBis() {
        Joueur joueurBlanc = new Joueur("Lacombe", "Dorian", true);
        Joueur joueurNoir = new Joueur("Fournier", "Quentin", false);
        ManagerJoueur managerJoueur = new ManagerJoueur(joueurBlanc, joueurNoir);
        System.out.println(joueurBlanc.getNombrePartiesJouees());
    }

}
