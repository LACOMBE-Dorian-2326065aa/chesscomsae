package fr.iut.chesscomsae;

import fr.iut.chesscomsae.piece.Piece;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class ChessController implements Initializable {

    /**
     * Initialisation de tous les éléments nécessaires implémentés en FXML
     */
    @FXML
    private GridPane chessBoard;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private Label labelPlaying;
    @FXML
    private Label labelGames;
    @FXML
    private Label timerMe;
    @FXML
    private Label timerEnnemy;
    @FXML
    private Button buttonPlay;
    @FXML
    private VBox newButtons;
    @FXML
    private Label nicknameMe;
    @FXML
    private Label nicknameEnnemy;

    private Label prenomLabel;
    private TextField prenom;
    private Label nomLabel;
    private TextField nom;
    private Button valid;

    private Joueur j1;
    private Joueur j2;
    private Plateau plateau;

    /**
     * Initialise les données de la fenêtre
     * @author Dorian Lacombe
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createChessBoard();
        createBindings();
        choiceBox.getSelectionModel().select("10 min");
        labelPlaying.setText(Integer.toString(ThreadLocalRandom.current().nextInt(100000, 200001)));
        labelGames.setText(Integer.toString(ThreadLocalRandom.current().nextInt(13000000, 14000001)));
    }

    /**
     * Crée les 8x8 cases du plateau en mettant automatiquement les couleurs
     * @author Dorian Lacombe
     */
    public void createChessBoard() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                Pane pane = new Pane();

                Color color;
                if((i+j) % 2 != 0) {
                    color = Color.rgb(115,149,82);
                } else {
                    color = Color.rgb(235,236,208);
                }

                Rectangle rect = new Rectangle(75, 75, color);
                pane.getChildren().add(rect);
                chessBoard.add(pane, j, i);
            }
        }
    }

    public void createBindings() {
        timerMe.textProperty().bind(Bindings.createStringBinding(() -> {
            return choiceBox.getSelectionModel().getSelectedItem() != null ? choiceBox.getSelectionModel().getSelectedItem().replace(" min", ":00").replace("5", "05") : "00:00";
        }, choiceBox.getSelectionModel().selectedItemProperty()));

        timerEnnemy.textProperty().bind(Bindings.createStringBinding(() -> {
            return choiceBox.getSelectionModel().getSelectedItem() != null ? choiceBox.getSelectionModel().getSelectedItem().replace(" min", ":00").replace("5", "05") : "00:00";
        }, choiceBox.getSelectionModel().selectedItemProperty()));
    }

    /**
     * Fonction exécutée lorsqu'on clique sur le bouton JOUER qui démarre la partie
     * @author Dorian Lacombe
     */
    public void play() {
        buttonPlay.setDisable(true);
        choiceBox.setDisable(true);

        prenomLabel = new Label("Prénom J1 :");
        prenom = new TextField();
        nomLabel = new Label("Nom J1 :");
        nom = new TextField();
        valid = new Button("Valider");

        prenomLabel.getStyleClass().add("prenomLabel");
        prenom.getStyleClass().add("prenom");
        nomLabel.getStyleClass().add("nomLabel");
        nom.getStyleClass().add("nom");
        valid.getStyleClass().add("valid");

        newButtons.getChildren().addAll(prenomLabel, prenom, nomLabel, nom, valid);

        valid.onActionProperty().set(actionEvent -> validation(true));
    }

    public void validation(boolean isJ1) {
        if(isJ1) {
            j1 = new Joueur(nom.getText(), prenom.getText(), true);
            prenomLabel.setText("Prénom J2 :");
            nomLabel.setText("Nom J2 :");
            nom.setText("");
            prenom.setText("");
            valid.onActionProperty().set(actionEvent -> validation(false));
        } else {
            j2 = new Joueur(nom.getText(), prenom.getText(), false);
            newButtons.getChildren().removeAll(prenom, prenomLabel, nom, nomLabel, valid);
            nicknameMe.setText(j1.getPrenom() + " " + j1.getNom() + " (" + j1.getNombrePartiesGagnees() + " / " + j1.getNombrePartiesJouees() + ")");
            nicknameEnnemy.setText(j2.getPrenom() + " " + j2.getNom() + " (" + j2.getNombrePartiesGagnees() + " / " + j2.getNombrePartiesJouees() + ")");
            displayGame();
        }
    }

    public void displayGame() {
        plateau = new Plateau(j1, j2);
        plateau.init();
        ArrayList<ArrayList<Piece>> partie = plateau.getTableau();
        for(int i = 0; i < partie.size(); i++) {
            for(int j = 0; j < partie.get(i).size(); j++) {
                if(partie.get(i).get(j) == null) continue;
                Image newPieceImg = new Image(partie.get(i).get(j).getImage());
                ImageView newPiece = new ImageView(newPieceImg);
                newPiece.setFitHeight(75);
                newPiece.setFitWidth(75);
                chessBoard.add(newPiece, j, i);
            }
        }
    }
}
