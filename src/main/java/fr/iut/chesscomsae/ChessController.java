package fr.iut.chesscomsae;

import fr.iut.chesscomsae.piece.Piece;
import fr.iut.chesscomsae.piece.Pion;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import java.util.Iterator;
import java.util.List;
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

    private Piece cellSelected;
    private Node nodeSelected;
    private boolean isWhitePlaying;

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

                if((i+j) % 2 != 0) {
                    pane.getStyleClass().add("chess-pane-1");
                } else {
                    pane.getStyleClass().add("chess-pane-2");
                }

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
        isWhitePlaying = true;
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
            plateau = new Plateau(j1, j2);
            plateau.init();
            displayGame();
            handleClicks();
        }
    }

    public void displayGame() {
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

    public void clearAll() {
        List<Node> childrenToRemove = new ArrayList<>();
        for (Node node : chessBoard.getChildren()) {
            if (node instanceof ImageView) {
                childrenToRemove.add(node);
            }
        }
        chessBoard.getChildren().removeAll(childrenToRemove);
    }


    public void handleClicks() {
        chessBoard.setOnMouseClicked(event -> {
            int col = (int) (event.getX() / 75);
            int row = (int) (event.getY() / 75);

            handleCellClick(row, col);
        });
    }

    public void handleCellClick(int row, int col) {
        clearMoves();
        if(plateau.getTableau().get(row).get(col) != null && ((isWhitePlaying && cellSelected == null && !plateau.getTableau().get(row).get(col).estBlanc()) || (!isWhitePlaying && cellSelected == null && plateau.getTableau().get(row).get(col).estBlanc()))) return;
        if(cellSelected != null && plateau.getTableau().get(row).get(col) == cellSelected){
            cellSelected = null;
            nodeSelected.getStyleClass().remove("selected");
            nodeSelected = null;
            return;
        }
        for(Node node : chessBoard.getChildren()) {
            if(GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                if(cellSelected == null && plateau.getTableau().get(row).get(col) != null) {
                    cellSelected = plateau.getTableau().get(row).get(col);
                    nodeSelected = node;
                    node.getStyleClass().add("selected");
                    displayMoves(cellSelected);
                    break;
                } else if(cellSelected != null) {
                    Piece previousCellSelected = new Pion(cellSelected.getLigne(), cellSelected.getColonne(), new Joueur("", "", true));
                    int hasPlayed = plateau.mouvement(cellSelected, row, col);
                    if(hasPlayed == 2){
                        isWhitePlaying = !isWhitePlaying;
                        cellSelected = null;
                        nodeSelected.getStyleClass().remove("selected");
                        nodeSelected = null;
                    } else if(hasPlayed == 1) {
                        cellSelected = plateau.getTableau().get(row).get(col);
                        nodeSelected.getStyleClass().remove("selected");
                        nodeSelected = node;
                        node.getStyleClass().add("selected");
                        displayMoves(cellSelected);
                    } else {
                        cellSelected = null;
                        nodeSelected.getStyleClass().remove("selected");
                        nodeSelected = null;
                    }
                    break;
                }
            } else {
                node.getStyleClass().remove("selected");
            }
        }
        clearAll();
        displayGame();
    }

    public void displayMoves(Piece piece) {
        ArrayList<int[]> moves = piece.mouvementsPossibles(plateau);
        for(Node node : chessBoard.getChildren()) {
            if (node instanceof Pane) {
                for(int[] move : moves) {
                    if(GridPane.getRowIndex(node) == move[0] && GridPane.getColumnIndex(node) == move[1]) {
                        if(plateau.getTableau().get(move[0]).get(move[1]) == null) node.getStyleClass().add("movable");
                        else node.getStyleClass().add("killable");
                    }
                }
            }
        }
    }

    public void clearMoves() {
        for(Node node : chessBoard.getChildren()) {
            if (node instanceof Pane) {
                node.getStyleClass().remove("movable");
                node.getStyleClass().remove("killable");
            }
        }
    }

}
