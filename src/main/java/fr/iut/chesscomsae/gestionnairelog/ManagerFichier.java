package fr.iut.chesscomsae.gestionnairelog;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class ManagerFichier {

    public FileReader lireFichier(String chemin) {
        try {
            return new FileReader(chemin);
        } catch (Exception e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
        return null;
    }

    public void afficherFichier(FileReader fichier) {
        try {
            int c;
            while ((c = fichier.read()) != -1) {
                System.out.print((char) c);
            }
            fichier.close();
        } catch (Exception e) {
            System.err.println("Erreur lors de l'affichage du fichier : " + e.getMessage());
        }
    }

    public void ecrireFichier(String chemin, String contenu) {
        try {
            FileWriter writer = new FileWriter(chemin);
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(contenu);
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            System.err.println("Erreur lors de l'écriture du fichier : " + e.getMessage());
        }
    }
}