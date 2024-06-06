package fr.iut.chesscomsae.gestionnairelog;

import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;

public class ManagerFichier {

    /**
     * Lit un fichier et le convertit en JsonArray
     * @author Valente Hugo
     * @param path le chemin du fichier
     * @return le JsonArray correspondant au fichier
     */
    private static JsonArray fichierVersJsonArray(String path) {
        try {
            FileReader reader = new FileReader(path);
            return new Gson().fromJson(reader, JsonArray.class);
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier " + path + " : " + e.getMessage());
        }
        return null;
    }


    /**
     * Lit un fichier et le convertit en liste de JsonObject correspondant à chaque élément du fichier
     * @author Valente Hugo
     * @param chemin le chemin du fichier
     * @return la liste de JsonObject correspondant au fichier
     */
    public static ArrayList<JsonObject> fichierVersListeJsonObject(String chemin) {
        JsonArray jsonArray = fichierVersJsonArray(chemin);
        ArrayList<JsonObject> jsonObjects = new ArrayList<>();
        if (jsonArray != null) {
            for (JsonElement jsonElement : jsonArray) {
                jsonObjects.add(jsonElement.getAsJsonObject());
            }
        }
        return jsonObjects;
    }

    /**
     * Ecrit une liste de JsonObject dans un fichier Json
     * @author Valente Hugo
     * @param jsonObjects la liste de JsonObject à écrire
     * @param chemin le chemin du fichier
     */
    public void listeJsonObjectVersFichier(ArrayList<JsonObject> jsonObjects, String chemin) {
        jsonObjects.removeIf(e -> e == null || e.isJsonNull() || e.entrySet().isEmpty() || e.entrySet().isEmpty() || e.entrySet().contains(null));
        try {
            FileWriter writer = new FileWriter(chemin);
            writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(jsonObjects));
            writer.close();
            System.out.println("Fichier " + chemin + " écrit avec succès");
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier " + chemin + " : " + e.getMessage());
        }
    }

}