package fr.iut.chesscomsae.gestionnairelog;

import com.google.gson.*;
import fr.iut.chesscomsae.Joueur;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManagerJoueur extends ManagerFichier {

    /**
     * Attribut de la classe ManagerJoueur
     */
    private static final String chemin = "joueurs.json";
    private static ArrayList<Joueur> joueurs = new ArrayList<>();

    public void ajouterJoueur(Joueur joueur) {
        joueurs.add(joueur);
        System.out.println(joueurs);
    }

    public void supprimerJoueur(Joueur joueur) {
        joueurs.remove(joueur);
    }

    public void effacerJoueurs() {
        joueurs.clear();
    }

    public void afficheFichierJoueurs() {
        afficherFichier(lireFichier(chemin));
    }

    public void chargerJoueurs() {
        List<String> lines = readFileToList(chemin);
        for (String line : lines) {
            System.out.println(line);
        }
    }

    public static List<String> readFileToList(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public void miseAJourJoueurs() {
        // On utilise la librairie Gson pour convertir notre liste de joueurs en JSON
        String json = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(joueurs);;
        // On appelle la méthode ecrireFichier de la classe ManagerFichier pour écrire le JSON dans le fichier
        ecrireFichier(chemin, json);
    }

}
