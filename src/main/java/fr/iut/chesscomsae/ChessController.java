package fr.iut.chesscomsae;

import fr.iut.chesscomsae.piece.Piece;
import fr.iut.chesscomsae.piece.Pion;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.*;
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
    @FXML
    private VBox popup;
    @FXML
    private Label popupLabel;
    @FXML
    private Button closePopup;
    @FXML
    private Button rematch;
    @FXML
    private VBox boxRight;
    @FXML
    private VBox buttonGames;
    @FXML
    private VBox buttonNewGame;
    @FXML
    private VBox buttonPlayers;

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

    private int time1;
    private int subTime1;
    private int time2;
    private int subTime2;
    private Timer timer;

    private ArrayList<Node> newGameContent;
    private ArrayList<Node> gamesContent;
    private ArrayList<Node> playersContent;

    private Joueur[] playersList;


    /**
     * Initialise les données de la fenêtre
     * @author Dorian Lacombe
     * @param location URL
     * @param resources Ressources
     * @author Dorian Lacombe
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createChessBoard();
        createBindings();
        choiceBox.getSelectionModel().select("10 min");
        labelPlaying.setText(Integer.toString(ThreadLocalRandom.current().nextInt(100000, 200001)));
        labelGames.setText(Integer.toString(ThreadLocalRandom.current().nextInt(13000000, 14000001)));

        buttonGames.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> windowGames());
        buttonNewGame.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> windowNewGame());
        buttonPlayers.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> windowPlayers());

        gamesContent = new ArrayList<>();
        playersContent = new ArrayList<>();
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
        Joueur jFictif1 = new Joueur(null, null, true);
        Joueur jFictif2 = new Joueur(null, null, false);
        Plateau plateauFictif = new Plateau(jFictif1, jFictif2);
        plateauFictif.init();
        displayGame(plateauFictif);
    }

    /**
     * Permet d'initialiser les bindings
     * @author Dorian Lacombe
     */
    public void createBindings() {
        timerMe.textProperty().bind(Bindings.createStringBinding(() -> {
            return choiceBox.getSelectionModel().getSelectedItem() != null ? choiceBox.getSelectionModel().getSelectedItem().replace(" min", ":00").replace("5", "05").replace("1:", "01:") : "00:00";
        }, choiceBox.getSelectionModel().selectedItemProperty()));

        timerEnnemy.textProperty().bind(Bindings.createStringBinding(() -> {
            return choiceBox.getSelectionModel().getSelectedItem() != null ? choiceBox.getSelectionModel().getSelectedItem().replace(" min", ":00").replace("5", "05").replace("1:", "01:") : "00:00";
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
        if(j1 == null && j2 == null) {
            newButtons.getChildren().addAll(prenomLabel, prenom, nomLabel, nom, valid);
            valid.onActionProperty().set(actionEvent -> validation(true));
        } else {
            initGame();
        }

        closePopup.onActionProperty().set(actionEvent -> closePopup());
        rematch.onActionProperty().set(actionEvent -> rematch());
    }

    /**
     * Permet d'enregistrer les noms et prénoms entrés par les joueurs et d'initialiser les "écouteurs" de clic
     * @param isJ1 Booléen pour savoir si c'est le joueur 1 qui envoie ses informations, ou le 2
     * @author Dorian Lacombe
     */
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
            initGame();
        }
    }

    public void initGame() {
        nicknameMe.setText(j1.getPrenom() + " " + j1.getNom() + " (" + j1.getNombrePartiesGagnees() + " / " + j1.getNombrePartiesJouees() + ")");
        nicknameEnnemy.setText(j2.getPrenom() + " " + j2.getNom() + " (" + j2.getNombrePartiesGagnees() + " / " + j2.getNombrePartiesJouees() + ")");
        clearAll();
        plateau = new Plateau(j1, j2);
        plateau.init();
        displayGame(plateau);
        handleClicks();
        if(!timerMe.getText().substring(0, 2).contains(":")) {
            time1 = Integer.parseInt(timerMe.getText().substring(0, 2));
            time2 = Integer.parseInt(timerEnnemy.getText().substring(0, 2));
        } else {
            time1 = Integer.parseInt(timerMe.getText().substring(0, 1));
            time2 = Integer.parseInt(timerEnnemy.getText().substring(0, 1));
        }
        subTime1 = 0;
        subTime2 = 0;
        timerLoop();
    }

    /**
     * Permet d'afficher les pièces dans la fenêtre à partir d'une liste de listes
     * @param plateau Plateau de jeu
     * @author Dorian Lacombe
     */
    public void displayGame(Plateau plateau) {

        System.out.println("joueur blanc joue : " + isWhitePlaying);
        System.out.println("echec et mat : " + plateau.echecEtMat(isWhitePlaying));
        System.out.println("coord roi noir : " + plateau.getCoordonnees(plateau.roiNoir)[0] + "," + plateau.getCoordonnees(plateau.roiNoir)[1]);
        System.out.println("roi noir immobile : " + plateau.coordonnees(plateau.piecesBlanches()).contains(plateau.getCoordonnees(plateau.roiNoir)) + "\n");

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

    /**
     * Permet de vider le plateau de jeu graphique avec de tout afficher de nouveau
     * @author Dorian Lacombe
     */
    public void clearAll() {
        List<Node> childrenToRemove = new ArrayList<>();
        for (Node node : chessBoard.getChildren()) {
            if (node instanceof ImageView) {
                childrenToRemove.add(node);
            }
        }
        chessBoard.getChildren().removeAll(childrenToRemove);
    }

    /**
     * Permet d'écouter les clics sur le GridPane (plateau de jeu) et de récupérer la cellule cliquée
     */
    public void handleClicks() {
        chessBoard.setOnMouseClicked(event -> {
            int col = (int) (event.getX() / 75);
            int row = (int) (event.getY() / 75);

            handleCellClick(row, col);
        });
    }

    /**
     * Permet de gérer tout ce qui est lié au clic dans une cellule, à savoir la sélection d'une pièce, son déplacement, etc..
     * @param row Numéro de la ligne concernée
     * @param col Numéro de la colonne concernée
     * @author Dorian Lacombe
     */
    public void handleCellClick(int row, int col) {
        clearMoves();
        if(plateau.getTableau().get(row).get(col) != null && ((isWhitePlaying && cellSelected == null && !plateau.getTableau().get(row).get(col).estBlanc()) || (!isWhitePlaying && cellSelected == null && plateau.getTableau().get(row).get(col).estBlanc()))) return;
        if(cellSelected != null && plateau.getTableau().get(row).get(col) == cellSelected){
            cellSelected = null;
            nodeSelected.getStyleClass().remove("selectedCell");
            nodeSelected = null;
            return;
        }
        for(Node node : chessBoard.getChildren()) {
            if(GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                if(cellSelected == null && plateau.getTableau().get(row).get(col) != null) {
                    cellSelected = plateau.getTableau().get(row).get(col);
                    nodeSelected = node;
                    node.getStyleClass().add("selectedCell");
                    displayMoves(cellSelected);
                    break;
                } else if(cellSelected != null) {
                    Piece previousCellSelected = new Pion(cellSelected.getLigne(), cellSelected.getColonne(), new Joueur("", "", true));
                    int hasPlayed = plateau.mouvement(cellSelected, row, col);
                    if(hasPlayed == 2){
                        if(cellSelected instanceof Pion) ((Pion) cellSelected).setPremierCoup(false);
                        isWhitePlaying = !isWhitePlaying;
                        cellSelected = null;
                        nodeSelected.getStyleClass().remove("selectedCell");
                        nodeSelected = null;
                    } else if(hasPlayed == 1) {
                        cellSelected = plateau.getTableau().get(row).get(col);
                        nodeSelected.getStyleClass().remove("selectedCell");
                        nodeSelected = node;
                        node.getStyleClass().add("selectedCell");
                        displayMoves(cellSelected);
                    } else {
                        cellSelected = null;
                        nodeSelected.getStyleClass().remove("selectedCell");
                        nodeSelected = null;
                    }
                    break;
                }
            } else {
                node.getStyleClass().remove("selectedCell");
            }
        }
        clearAll();
        displayGame(plateau);
    }

    /**
     * Permet d'afficher graphiquement les cases où une pièce peut se déplacer une fois cliquée
     * @param piece Pièce dont les mouvements possibles doivent être ajoutés
     * @author Dorian Lacombe
     */
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


    /**
     * Permet de vider tout l'affichage graphique des mouvements possibles des pièces
     * @author Dorian Lacombe
     */
    public void clearMoves() {
        for(Node node : chessBoard.getChildren()) {
            if (node instanceof Pane) {
                node.getStyleClass().remove("movable");
                node.getStyleClass().remove("killable");
            }
        }
    }

    public void timerLoop() {
        timerMe.textProperty().unbind();
        timerEnnemy.textProperty().unbind();
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (isWhitePlaying) {
                        //subTime1--;
                        subTime1 -= 20;
                        if (subTime1 <= -1) {
                            subTime1 = 59;
                            time1--;
                            if (time1 == -1) {
                                time1 = 0;
                                subTime1 = 0;
                            }
                        }
                    } else {
                        subTime2--;
                        if (subTime2 <= -1) {
                            subTime2 = 59;
                            time2--;
                            if (time2 == -1) {
                                time2 = 0;
                                subTime2 = 0;
                            }
                        }
                    }
                    timerMe.textProperty().bind(Bindings.concat(String.format("%02d:%02d", time1, subTime1)));
                    timerEnnemy.textProperty().bind(Bindings.concat(String.format("%02d:%02d", time2, subTime2)));

                    if(time1 == 0 && subTime1 == 0 && isWhitePlaying) {
                        initEnd(j2.getPrenom() + " " + j2.getNom() + " a gagné la partie grâce au temps !");
                    } else if(time2 == 0 && subTime2 == 0 && !isWhitePlaying) {
                        initEnd(j1.getPrenom() + " " + j1.getNom() + " a gagné la partie grâce au temps !");
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    public void initEnd(String msg) {
        popupLabel.setText(msg);
        popup.getStyleClass().add("visible");
        popupLabel.setWrapText(true);
        timer.cancel();
        timer.purge();
        chessBoard.setOnMouseClicked(null);
        if(nodeSelected != null)
            nodeSelected.getStyleClass().remove("selectedCell");
        clearMoves();
        cellSelected = null;
        nodeSelected = null;
    }

    public void closePopup() {
        popup.getStyleClass().remove("visible");
        buttonPlay.setDisable(false);
        choiceBox.setDisable(false);
        createBindings();
        j1 = null;
        j2 = null;
        nicknameMe.setText("Moi");
        nicknameEnnemy.setText("Adversaire");
    }

    public void rematch() {
        popup.getStyleClass().remove("visible");
        createBindings();
        play();
    }

    public void windowNewGame() {
        if(newGameContent == null) return;
        buttonGames.getStyleClass().removeAll("menuRight", "menuRightActual");
        buttonPlayers.getStyleClass().removeAll("menuRight", "menuRightActual");
        buttonNewGame.getStyleClass().removeAll("menuRight", "menuRightActual");
        buttonNewGame.getStyleClass().add("menuRightActual");
        buttonPlayers.getStyleClass().add("menuRight");
        buttonGames.getStyleClass().add("menuRight");
        if(gamesContent == null) {
            gamesContent = new ArrayList<>(boxRight.getChildren());
        }else if(playersContent == null) {
            playersContent = new ArrayList<>(boxRight.getChildren());
        }
        boxRight.getChildren().clear();
        boxRight.getChildren().addAll(newGameContent);
        newGameContent = null;
    }

    public void windowGames() {
        if(gamesContent == null) return;
        buttonGames.getStyleClass().removeAll("menuRight", "menuRightActual");
        buttonPlayers.getStyleClass().removeAll("menuRight", "menuRightActual");
        buttonNewGame.getStyleClass().removeAll("menuRight", "menuRightActual");
        buttonGames.getStyleClass().add("menuRightActual");
        buttonPlayers.getStyleClass().add("menuRight");
        buttonNewGame.getStyleClass().add("menuRight");
        if(newGameContent == null) {
            newGameContent = new ArrayList<>(boxRight.getChildren());
        }else if(playersContent == null) {
            playersContent = new ArrayList<>(boxRight.getChildren());
        }
        boxRight.getChildren().clear();
        boxRight.getChildren().addAll(gamesContent);
        gamesContent = null;
    }

    public void windowPlayers() {
        if(playersContent == null) return;
        buttonGames.getStyleClass().removeAll("menuRight", "menuRightActual");
        buttonPlayers.getStyleClass().removeAll("menuRight", "menuRightActual");
        buttonNewGame.getStyleClass().removeAll("menuRight", "menuRightActual");
        buttonPlayers.getStyleClass().add("menuRightActual");
        buttonGames.getStyleClass().add("menuRight");
        buttonNewGame.getStyleClass().add("menuRight");
        if(newGameContent == null) {
            newGameContent = new ArrayList<>(boxRight.getChildren());
        }else if(gamesContent == null) {
            gamesContent = new ArrayList<>(boxRight.getChildren());
        }
        boxRight.getChildren().clear();
        boxRight.getChildren().addAll(playersContent);
        playersContent = null;
        loadPlayers();
    }

    public void loadPlayers() {
        boxRight.getChildren().clear();
        playersList = new Joueur[]{j1, j2};
        for(Joueur j : playersList) {
            if(j == null) continue;
            HBox playerInList = new HBox();
            playerInList.getStyleClass().add("playerInList");
            Image img = new Image("file:src/main/resources/img/profile_picture.png");
            ImageView imgV = new ImageView(img);
            imgV.setFitHeight(30);
            imgV.setFitWidth(30);
            Label jLabelPrenom = new Label(j.getPrenom());
            Label jLabelNom = new Label(j.getNom());
            Label jLabelWon = new Label(Integer.toString(j.getNombrePartiesGagnees()));
            Label jLabelPlayed = new Label(Integer.toString(j.getNombrePartiesJouees()));
            jLabelWon.getStyleClass().add("stat");
            jLabelPlayed.getStyleClass().add("stat");
            jLabelPrenom.setAlignment(Pos.CENTER);
            jLabelNom.setAlignment(Pos.CENTER);
            jLabelPlayed.setAlignment(Pos.CENTER);
            jLabelWon.setAlignment(Pos.CENTER);
            playerInList.getChildren().addAll(imgV, jLabelPrenom, jLabelNom, jLabelWon, jLabelPlayed);
            boxRight.getChildren().add(playerInList);
        }
        Region rg = new Region();
        VBox.setVgrow(rg, Priority.ALWAYS);
        boxRight.getChildren().add(rg);
    }

}
