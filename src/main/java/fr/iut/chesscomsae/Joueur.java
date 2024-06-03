package fr.iut.chesscomsae;

import java.io.*;
import java.util.*;

public class Joueur implements Serializable {
    private String nom;
    private String prenom;
    private int nombrePartiesJouees;
    private int nombrePartiesGagnees;
    private boolean isWhite;



    public Joueur(String nom, String prenom, boolean isWhite) {
        this.nom = nom;
        this.prenom = prenom;
        this.nombrePartiesJouees = 0;
        this.nombrePartiesGagnees = 0;
        this.isWhite = isWhite;
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
    public boolean isWhite() {
        return isWhite;
    }

    // Setter pour la couleur
    public void setWhite(boolean isWhite) {
        this.isWhite = isWhite;
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
    public String toString() {
        return "Joueur{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nombrePartiesJouees=" + nombrePartiesJouees +
                ", nombrePartiesGagnees=" + nombrePartiesGagnees +
                ", isWhite=" + (isWhite ? "Blanc" : "Noir") +
                '}';
    }
}



