package fr.iut.chesscomsae.gestionnairelog;

import com.google.gson.JsonObject;
import fr.iut.chesscomsae.Joueur;
import fr.iut.chesscomsae.Partie;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ManagerParties extends ManagerFichier{

    private final static String CHEMIN = "parties.json";

    private final static String JOUEUR_GAGNANT = "gagnant", JOUEUR_PERDANT = "perdant", isPAT = "PAT";

    public ManagerParties() {
        super();
        File file = new File(CHEMIN);
        if (!file.exists()) {
            System.err.println("Le fichier " + CHEMIN + " n'existe pas");
            try {
                System.out.println("Création du fichier " + CHEMIN);
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Erreur lors de la création du fichier " + CHEMIN + " : " + e.getMessage());
            }
        }
    }

    /**
     * Permet d'obtenir la liste des parties depuis le fichier .json associé aux parties
     * @return la liste des parties
     */
    public static ArrayList<Partie> getParties() {
        ArrayList<JsonObject> jsonObjects = fichierVersListeJsonObject(CHEMIN);
        ArrayList<Partie> parties = new ArrayList<>();
        for (JsonObject e : jsonObjects) {
            Joueur joueurGagnant = new Joueur(e.get(JOUEUR_GAGNANT).getAsString().split("/§/")[0], e.get(JOUEUR_GAGNANT).getAsString().split("/§/")[1]);
            Joueur joueurPerdant = new Joueur(e.get(JOUEUR_PERDANT).getAsString().split("/§/")[0], e.get(JOUEUR_PERDANT).getAsString().split("/§/")[1]);
            boolean PAT = e.get(isPAT).getAsBoolean();
            parties.add(new Partie(joueurGagnant, joueurPerdant, PAT));
        }
        return parties;
    }

    /**
     * Permet d'ajouter une partie au fichier .json associé aux parties
     * @param gagnant le joueur blanc
     * @param perdant le joueur noir
     */
    public void ajouterPartie(Joueur gagnant, Joueur perdant, boolean PAT) {
        ArrayList<JsonObject> jsonObjects = fichierVersListeJsonObject(CHEMIN);
        JsonObject e = new JsonObject();
        e.addProperty(JOUEUR_GAGNANT, gagnant.getNom() + "/§/" + gagnant.getPrenom());
        e.addProperty(JOUEUR_PERDANT, perdant.getNom() + "/§/" + perdant.getPrenom());
        e.addProperty(isPAT, PAT);
        jsonObjects.add(e);
        listeJsonObjectVersFichier(jsonObjects, CHEMIN);
    }

}
