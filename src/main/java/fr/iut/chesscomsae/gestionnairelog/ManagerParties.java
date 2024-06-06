package fr.iut.chesscomsae.gestionnairelog;

import com.google.gson.JsonObject;
import fr.iut.chesscomsae.Joueur;
import fr.iut.chesscomsae.Partie;

import java.util.ArrayList;

public class ManagerParties extends ManagerFichier{

    private final static String CHEMIN = "parties.json";

    private final static String JOUEUR_BLANC = "joueurBlanc", JOUEUR_NOIR = "joueurNoir", GAGNANT = "gagnant";

    public ManagerParties() {
        super();
    }

    public static ArrayList<Partie> getParties() {
        ArrayList<JsonObject> jsonObjects = fichierVersListeJsonObject(CHEMIN);
        ArrayList<Partie> parties = new ArrayList<>();
        for (JsonObject e : jsonObjects) {
            Joueur joueurBlanc = new Joueur(e.get(JOUEUR_BLANC).getAsString().split(" ")[0], e.get(JOUEUR_BLANC).getAsString().split(" ")[1]);
            Joueur joueurNoir = new Joueur(e.get(JOUEUR_NOIR).getAsString().split(" ")[0], e.get(JOUEUR_NOIR).getAsString().split(" ")[1]);
            Joueur gagnant = new Joueur(e.get(GAGNANT).getAsString().split(" ")[0], e.get(GAGNANT).getAsString().split(" ")[1]);
            parties.add(new Partie(joueurBlanc, joueurNoir, gagnant));
        }
        return parties;
    }

    public void ajouterPartie(Joueur joueurBlanc, Joueur joueurNoir, Joueur gagnant) {
        ArrayList<JsonObject> jsonObjects = fichierVersListeJsonObject(CHEMIN);
        JsonObject e = new JsonObject();
        e.addProperty(JOUEUR_BLANC, joueurBlanc.getNom() + " " + joueurBlanc.getPrenom());
        e.addProperty(JOUEUR_NOIR, joueurNoir.getNom() + " " + joueurNoir.getPrenom());
        e.addProperty(GAGNANT, gagnant.getNom() + " " + gagnant.getPrenom());
        jsonObjects.add(e);
        listeJsonObjectVersFichier(jsonObjects, CHEMIN);
    }

}
