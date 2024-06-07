package fr.iut.chesscomsae;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;

public class Chess extends Application {

    /**
     * Initialiser l'application et l'affiche à l'écran
     * @param stage L'élément stage contenant la scene de notre fenêtre
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        StackPane root = FXMLLoader.load(getClass().getClassLoader().getResource("chess.fxml"));
        Scene scene = new Scene(root, 1080, 720);

        String css = getClass().getClassLoader().getResource("style.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.getIcons().add(new Image("file:src/main/resources/img/logo.png"));
        stage.setTitle("Chess.com");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Lance l'applications
     * @param args Paramètres
     */
    public static void main(String[] args) {
        launch();
    }
}