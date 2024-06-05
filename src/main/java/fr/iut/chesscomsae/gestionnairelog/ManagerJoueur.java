package fr.iut.chesscomsae.gestionnairelog;

import com.google.gson.*;
import fr.iut.chesscomsae.Joueur;


import java.util.ArrayList;
import java.util.Arrays;
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
     * Tableau des joueurs en jeu
     */
    private static final Joueur[] joueursEnJeu = new Joueur[2];

    /**
     * Constructeur de la classe ManagerJoueur
     * @author Valente Hugo
     * @param joueurBlanc le joueur blanc
     * @param joueurNoir le joueur noir
     */
    public ManagerJoueur(Joueur joueurBlanc, Joueur joueurNoir) {
        super();
        joueursEnJeu[0] = joueurBlanc;
        joueursEnJeu[1] = joueurNoir;
        ajouterJoueur(joueurBlanc);
        ajouterJoueur(joueurNoir);
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
     * @autor Valente Hugo
     * @param joueur le joueur à mettre à jour
     */
    private void miseAJourJoueur(Joueur joueur) {
        for (Joueur j : getJoueurs()) {
            if (j.getNom().equals(joueur.getNom()) && j.getPrenom().equals(joueur.getPrenom())) {
                joueur.setNombrePartiesJouees(j.getNombrePartiesJouees());
                joueur.setNombrePartiesGagnees(j.getNombrePartiesGagnees());
            }
        }
    }

    /**
     * Ajoute un joueur dans le fichier .json associé aux joueurs
     * @author Valente Hugo
     * @param joueur le joueur à ajouter
     */
    public void ajouterJoueur(Joueur joueur) {
        if (joueur == null) return;
        if (joueurExiste(joueur)) {
            System.out.println("Le joueur "+ joueur.getPrenom() + " " + joueur.getNom() + " existe déjà");
            miseAJourJoueur(joueur);
            return;
        }
        ArrayList<JsonObject> jsonObjects = fichierVersListeJsonObject(CHEMIN);
        JsonObject e = new JsonObject();
        e.addProperty(NOM, joueur.getNom());
        e.addProperty(PRENOM, joueur.getPrenom());
        e.addProperty(PARTIES_JOUEES, joueur.getNombrePartiesJouees());
        e.addProperty(PARTIES_GAGNEES, joueur.getNombrePartiesGagnees());
        jsonObjects.add(e);
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
            if (Arrays.stream(joueursEnJeu).toList().contains(joueur)){
                for (Joueur j : joueursEnJeu) {
                    if (j.equals(joueur)) {
                        joueurs.add(j);
                    }
                }
            }else {
                joueurs.add(joueur);
            }
        }
        return joueurs;
    }

}
