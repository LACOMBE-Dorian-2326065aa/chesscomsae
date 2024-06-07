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
     * @author Baptiste Turmo
     * @param nom Nom du joueur
     * @param prenom Prénom du joueur
     * @param estBlanc Si le joueur est blanc ou non
     */
    public Joueur(String nom, String prenom, boolean estBlanc) {
        this.nom = nom;
        this.prenom = prenom;
        this.nombrePartiesJouees = 0;
        this.nombrePartiesGagnees = 0;
        this.estBlanc = estBlanc;
    }

    /**
     * Constructeur de la classe Joueur sans précision de la couleur
     * @author Hugo Valente
     * @param nom Nom du joueur
     * @param prenom Prénom du joueur
     */
    public Joueur(String nom, String prenom) {
        this(nom, prenom, false);
    }

    /**
     * Permet de changer le nombre de parties gagnées
     * @author Hugo Valente
     * @param nombrePartiesGagnees Nombre de parties gagnées
     */
    public void setNombrePartiesGagnees(int nombrePartiesGagnees) {
        this.nombrePartiesGagnees = nombrePartiesGagnees;
    }

    /**
     * Permet de changer le nombre de parties jouées
     * @author Hugo Valente
     * @param nombrePartiesJouees Nombre de parties jouées
     */
    public void setNombrePartiesJouees(int nombrePartiesJouees) {
        this.nombrePartiesJouees = nombrePartiesJouees;
    }

    /**
     * Permet de récupérer le nom du joueur
     * @author Baptiste Turmo
     * @return Nombre de parties jouées
     */
    public String getNom() {
        return nom;
    }

    /**
     * Permet de changer le nom du joueur
     * @author Baptiste Turmo
     * @param nom Nom du joueur
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Permet de récupérer le prénom du joueur
     * @author Baptiste Turmo
     * @return Prénom du joueur
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Permet de changer le prénom du joueur
     * @author Baptiste Turmo
     * @param prenom Prénom du joueur
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Permet d'obtenir le nombre de parties jouées par un joueur
     * @author Baptiste Turmo
     * @return Nombre de parties jouées
     */
    public int getNombrePartiesJouees() {
        return nombrePartiesJouees;
    }

    /**
     * Permet d'obtenir le nombre de parties gagnées par un joueur
     * @author Baptiste Turmo
     * @return Nombre de parties gagnées
     */
    public int getNombrePartiesGagnees() {
        return nombrePartiesGagnees;
    }

    /**
     * Permet de savoir si le joueur est blanc ou non
     * @author Baptiste Turmo
     * @return true si le joueur est blanc, false sinon
     */
    public boolean estBlanc() {
        return estBlanc;
    }

    /**
     * Permet de changer la couleur du joueur
     * @author Hugo Valente
     * @param estBlanc true si le joueur est blanc, false sinon
     */
    public void setEstBlanc(boolean estBlanc) {
        this.estBlanc = estBlanc;
    }

    /**
     * Permet d'avoir une représentation textuelle d'un joueur
     * @author Baptiste Turmo
     * @return Chaîne de caractères représentant un joueur
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



