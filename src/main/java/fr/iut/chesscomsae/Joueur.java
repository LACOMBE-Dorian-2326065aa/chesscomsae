package fr.iut.chesscomsae;

import com.google.gson.annotations.Expose;

public class Joueur {

    /**
     * Attributs de la classe Joueur
     */
    @Expose
    public String nom;
    @Expose
    public String prenom;
    @Expose
    public int nombrePartiesJouees;
    @Expose
    public int nombrePartiesGagnees;
    private boolean estBlanc;




    /**
     * Constructeur de la classe Joueur
     * @author Turmo Baptiste
     */
    public Joueur(String nom, String prenom, boolean estBlanc) {
        this.nom = nom;
        this.prenom = prenom;
        this.nombrePartiesJouees = 0;
        this.nombrePartiesGagnees = 0;
        this.estBlanc = estBlanc;
    }

    /**
     * Constructeur de la classe Joueur
     * @author Valente Hugo
     */
    public Joueur(String nom, String prenom) {
        this(nom, prenom, false);
    }

    /**
     * Permets de changer le nombre de parties gagnées
     * @author Valente Hugo
     */
    public void setNombrePartiesGagnees(int nombrePartiesGagnees) {
        this.nombrePartiesGagnees = nombrePartiesGagnees;
    }

    /**
     * Permets de changer le nombre de parties jouées
     * @author Valente Hugo
     * @param nombrePartiesJouees le nombre de parties jouées
     */
    public void setNombrePartiesJouees(int nombrePartiesJouees) {
        this.nombrePartiesJouees = nombrePartiesJouees;
    }

    /**
     * Permets de récupérer le nom du joueur
     * @author Turmo Baptiste
     * @return le nombre de parties jouées
     */
    public String getNom() {
        return nom;
    }

    /**
     * Permets de changer le nom du joueur
     * @author Turmo Baptiste
     * @param nom le nom du joueur
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Permets de récupérer le prénom du joueur
     * @author Turmo Baptiste
     * @return le prénom du joueur
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Permets de changer le prénom du joueur
     * @author Turmo Baptiste
     * @param prenom le prénom du joueur
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Permet d'obtenir le nombre de parties jouées par un joueur
     * @author Turmo Baptiste
     * @return le nombre de parties jouées
     */
    public int getNombrePartiesJouees() {
        return nombrePartiesJouees;
    }

    /**
     * Permet d'obtenir le nombre de parties gagnées par un joueur
     * @author Turmo Baptiste
     * @return le nombre de parties gagnées
     */
    public int getNombrePartiesGagnees() {
        return nombrePartiesGagnees;
    }

    /**
     * Permet de savoir si le joueur est blanc ou non
     * @author Turmo Baptiste
     * @return true si le joueur est blanc, false sinon
     */
    public boolean estBlanc() {
        return estBlanc;
    }

    /**
     * Permet de changer la couleur du joueur
     * @author Valente Hugo
     * @param estBlanc true si le joueur est blanc, false sinon
     */
    public void setEstBlanc(boolean estBlanc) {
        this.estBlanc = estBlanc;
    }

    /**
     * Permet d'avoir une représentation textuelle d'un joueur
     * @author Turmo Baptiste
     * @return une chaîne de caractères représentant un joueur
     */
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

}



