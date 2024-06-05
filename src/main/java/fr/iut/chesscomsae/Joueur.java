package fr.iut.chesscomsae;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.io.*;

public class Joueur {

    @Expose
    public String nom;
    @Expose
    public String prenom;
    @Expose
    public int nombrePartiesJouees;
    @Expose
    public int nombrePartiesGagnees;
    private boolean estBlanc;




    public Joueur(String nom, String prenom, boolean estBlanc) {
        this.nom = nom;
        this.prenom = prenom;
        this.nombrePartiesJouees = 0;
        this.nombrePartiesGagnees = 0;
        this.estBlanc = estBlanc;
    }

    public Joueur(String nom, String prenom) {
        this(nom, prenom, false);
    }




    // Ici on a les getters et les setters pour les attributs de la classe Joueur
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNombrePartiesJouees() {
        return nombrePartiesJouees;
    }

    public void incrementerNombrePartiesJouees() {
        this.nombrePartiesJouees++;
    }

    public int getNombrePartiesGagnees() {
        return nombrePartiesGagnees;
    }

    public void incrementerNombrePartiesGagnees() {
        this.nombrePartiesGagnees++;
    }

    // Getter pour la couleur
    public boolean estBlanc() {
        return estBlanc;
    }

    // Setter pour la couleur
    public void setEstBlanc(boolean estBlanc) {
        this.estBlanc = estBlanc;
    }

    // Ici on enregistre un joueur dans un fichier
    public void sauvegarderJoueur(String cheminFichier) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cheminFichier))) {
            oos.writeObject(this);
        }
    }


    // Ici on lit un joueur depuis un fichier
    public static Joueur lireJoueur(String cheminFichier) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cheminFichier))) {
            return (Joueur) ois.readObject();
        }
    }

    @Override
    public String toString() {
        return "Joueur[" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nombrePartiesJouees=" + nombrePartiesJouees +
                ", nombrePartiesGagnees=" + nombrePartiesGagnees +
                ", estBlanc=" + (estBlanc ? "true" : "false") +
                ']';
    }

    public String joueurToJson() {;
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create().toJson(this);
    }

}



