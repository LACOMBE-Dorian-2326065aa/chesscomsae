package fr.iut.chesscomsae.gestionnairelog;

import com.google.gson.*;
import fr.iut.chesscomsae.Joueur;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManagerJoueur extends ManagerFichier {

    /**
     * Chemin du fichier .json associé aux joueurs
     */
    private final static String CHEMIN = "joueurs.json";

    /**
     * Attributs du fichier .json associé aux joueurs
     */
    private final static String NOM = "nom", PRENOM = "prenom", PARTIES_JOUEES = "nombrePartiesJouees", PARTIES_GAGNEES = "nombrePartiesGagnees";

    /**
     * Constructeur de la classe ManagerJoueur
     * @author Valente Hugo
     */
    public ManagerJoueur() {
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
        }else {
            System.out.println("Le fichier " + CHEMIN + " existe");
        }
    }

    /**
     * Permet de savoir si un joueur est déjà présent dans le fichier .json associé aux joueurs
     * @author Valente Hugo
     * @return la liste des joueurs
     */
    public boolean joueurExiste(Joueur joueur) {
        if (joueur == null) return false;
        ArrayList<JsonObject> jsonObjects = fichierVersListeJsonObject(CHEMIN);
        for (JsonObject e : jsonObjects) {
            if (e.get(NOM).getAsString().equals(joueur.getNom()) && e.get(PRENOM).getAsString().equals(joueur.getPrenom())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Met à jour les informations d'un joueur s'il existe déjà dans le fichier .json associé aux joueurs
     * @author Valente Hugo
     * @param joueur le joueur à mettre à jour
     */
    public static void miseAJourJoueur(Joueur joueur) {
        for (Joueur j : getJoueurs()) {
            if (j.getNom().equalsIgnoreCase(joueur.getNom()) && j.getPrenom().equalsIgnoreCase(joueur.getPrenom())) {
                joueur.setNombrePartiesJouees(j.getNombrePartiesJouees());
                joueur.setNombrePartiesGagnees(j.getNombrePartiesGagnees());
                joueur.setNom(j.getNom());
                joueur.setPrenom(j.getPrenom());
            }
        }
    }

    /**
     * Convertit un joueur en JsonObject
     * @author Valente Hugo
     * @param joueur le joueur à convertir
     * @return le joueur converti en JsonObject
     */
    public static JsonObject joueurVersJsonObject(Joueur joueur) {
        JsonObject e = new JsonObject();
        e.addProperty(NOM, joueur.getNom().replace(" ", ""));
        e.addProperty(PRENOM, joueur.getPrenom().replace(" ", ""));
        e.addProperty(PARTIES_JOUEES, joueur.getNombrePartiesJouees());
        e.addProperty(PARTIES_GAGNEES, joueur.getNombrePartiesGagnees());
        System.out.println(e);
        return e;
    }
    /**
     * Ajoute un joueur dans le fichier .json associé aux joueurs, met à jour les informations du joueur s'il existe déjà
     * @author Valente Hugo
     * @param joueur le joueur à ajouter
     */
    public void ajouterJoueur(Joueur joueur) {
        if (joueur == null) return;
        if (joueur.getNom().startsWith(" ") && joueur.getPrenom().startsWith(" ")) {
            joueur.setNom("Féroce");
            joueur.setPrenom("Souris");
        }
        if (joueurExiste(joueur)) {
            System.out.println("Le joueur "+ joueur.getPrenom() + " " + joueur.getNom() + " existe déjà");
            miseAJourJoueur(joueur);
            return;
        }
        ArrayList<JsonObject> jsonObjects = fichierVersListeJsonObject(CHEMIN);
        jsonObjects.add(joueurVersJsonObject(joueur));
        System.out.println("Joueur ajouté : " + joueur.getPrenom() + " " + joueur.getNom());
        listeJsonObjectVersFichier(jsonObjects, CHEMIN);
    }

    /**
     * Modifie les informations d'un joueur dans le fichier .json associé aux joueurs
     * @author Valente Hugo
     * @param joueur le joueur à modifier
     * @param totalPartiesJouees le nombre total de parties jouées
     * @param totalPartiesGagnees le nombre total de parties gagnées
     */
    public void modifieJoueurInformation(Joueur joueur, int totalPartiesJouees, int totalPartiesGagnees) {
        if (!joueurExiste(joueur)) {
            ajouterJoueur(joueur);
        }
        ArrayList<JsonObject> jsonObjects = fichierVersListeJsonObject(CHEMIN);
        for (JsonObject e : jsonObjects) {
            if (e.get(NOM).getAsString().equals(joueur.getNom()) && e.get(PRENOM).getAsString().equals(joueur.getPrenom())) {
                System.out.println("Modification du joueur : " + joueur.getPrenom() + " " + joueur.getNom());
                e.addProperty(PARTIES_JOUEES, totalPartiesJouees);
                e.addProperty(PARTIES_GAGNEES, totalPartiesGagnees);
                listeJsonObjectVersFichier(jsonObjects, CHEMIN);
                miseAJourJoueur(joueur);
                break;
            }
        }

    }

    /**
     * Récupère la liste des joueurs dans le fichier .json associé aux joueurs
     * @author Valente Hugo
     * @return la liste des joueurs
     */
    public static List<Joueur> getJoueurs() {
        ArrayList<JsonObject> jsonObjects = fichierVersListeJsonObject(CHEMIN);
        List<Joueur> joueurs = new ArrayList<>();
        for (JsonObject e : jsonObjects) {
            Joueur joueur = new Joueur(e.get(NOM).getAsString(), e.get(PRENOM).getAsString());
            joueur.setNombrePartiesJouees(e.get(PARTIES_JOUEES).getAsInt());
            joueur.setNombrePartiesGagnees(e.get(PARTIES_GAGNEES).getAsInt());
            joueurs.add(joueur);
        }
        return joueurs;
    }

}
