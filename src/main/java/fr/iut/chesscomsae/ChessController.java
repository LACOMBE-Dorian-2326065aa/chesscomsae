package fr.iut.chesscomsae;

import fr.iut.chesscomsae.gestionnairelog.ManagerJoueur;
import fr.iut.chesscomsae.gestionnairelog.ManagerParties;
import fr.iut.chesscomsae.piece.Piece;
import fr.iut.chesscomsae.piece.Pion;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;

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
    private VBox popupTournoi;
    @FXML
    private Label popupTournoiLabel;
    @FXML
    private Button popupSuivant;
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
    @FXML
    private Button buttonTournoi;
    @FXML
    private VBox popupFinTournoi;
    @FXML
    private Label popupFinTournoiLabel;
    @FXML
    private Button popupFin;
    @FXML
    private HBox annonce;
    @FXML
    private VBox popupMatch;
    @FXML
    private Label popupMatchLabel;
    @FXML
    private Button matchButton;

    private Label prenomLabel;
    private TextField prenom;
    private Label nomLabel;
    private TextField nom;
    private Button valid;
    private Button againstBot;

    private Joueur j1;
    private Joueur j2;
    private Joueur winner;
    private Joueur loser;
    private Plateau plateau;

    private ArrayList<Joueur> joueursTournoi;
    private int nbJoueursTextField;
    private ArrayList<Joueur> joueursAyantJoue;
    private int nombreJoueursTournoi;
    private ArrayList<Integer> indexList;
    private int dernierIndex;
    private ArrayList<Joueur> joueursElimines;
    private int nbMatch;

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
    private ArrayList<Piece> piecesNoires;

    private Joueur[] playersList;
    private Partie[] gamesList;

    private ManagerJoueur managerJoueur;
    private boolean modeTournoi;

    private VBox vBoxTournoi;


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

        dernierIndex = 0;
        nbMatch = 1;
        joueursElimines = new ArrayList<>();
        indexList = new ArrayList<>();
        joueursAyantJoue = new ArrayList<>();
        joueursTournoi = new ArrayList<>();
        gamesContent = new ArrayList<>();
        playersContent = new ArrayList<>();
        newGameContent = null;
        managerJoueur = new ManagerJoueur();
        modeTournoi = false;

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
        buttonTournoi.setDisable(true);
        prenomLabel = new Label("Prénom J1 :");
        prenom = new TextField();
        nomLabel = new Label("Nom J1 :");
        nom = new TextField();
        valid = new Button("Jouer vs J2");
        againstBot = new Button("Jouer vs BOT");
        isWhitePlaying = true;
        prenomLabel.getStyleClass().add("prenomLabel");
        prenom.getStyleClass().add("prenom");
        nomLabel.getStyleClass().add("nomLabel");
        nom.getStyleClass().add("nom");
        valid.getStyleClass().add("valid");
        valid.disableProperty().bind(Bindings.createBooleanBinding(() -> prenom.getText().equals("") || nom.getText().equals(""), prenom.textProperty(), nom.textProperty()));
        againstBot.getStyleClass().add("againstBot");
        againstBot.disableProperty().bind(Bindings.createBooleanBinding(() -> prenom.getText().equals("") || nom.getText().equals(""), prenom.textProperty(), nom.textProperty()));

        if(j1 == null && j2 == null) {
            newButtons.getChildren().addAll(prenomLabel, prenom, nomLabel, nom, valid, againstBot);
            valid.onActionProperty().set(actionEvent -> validation(true));
            againstBot.onActionProperty().set(actionEvent -> playAgainstBot());
        } else {
            initGame();
        }

        closePopup.onActionProperty().set(actionEvent -> closePopup());
        rematch.onActionProperty().set(actionEvent -> rematch());
    }

    /**
     * Prépare l'initialisation d'une partie contre un BOT
     * @author Dorian Lacombe
     */
    public void playAgainstBot() {
        j1 = new Joueur(nom.getText(), prenom.getText(), true);
        j2 = new Joueur("BOT", " ", false);
        managerJoueur.ajouterJoueur(j1);
        managerJoueur.ajouterJoueur(j2);
        nom.setText("");
        prenom.setText("");
        newButtons.getChildren().removeAll(prenom, prenomLabel, nom, nomLabel, valid, againstBot);
        initGame();
    }

    /**
     * Permet d'enregistrer les noms et prénoms entrés par les joueurs et d'initialiser les "écouteurs" de clic
     * @param isJ1 Booléen pour savoir si c'est le joueur 1 qui envoie ses informations, ou le 2
     * @author Dorian Lacombe
     */
    public void validation(boolean isJ1) {
        if(isJ1) {
            j1 = new Joueur(nom.getText(), prenom.getText(), true);
            managerJoueur.ajouterJoueur(j1);
            prenomLabel.setText("Prénom J2 :");
            nomLabel.setText("Nom J2 :");
            nom.setText("");
            prenom.setText("");
            valid.setText("Commencer");
            newButtons.getChildren().remove(againstBot);
            valid.onActionProperty().set(actionEvent -> validation(false));
        } else {
            j2 = new Joueur(nom.getText(), prenom.getText(), false);
            managerJoueur.ajouterJoueur(j2);
            newButtons.getChildren().removeAll(prenom, prenomLabel, nom, nomLabel, valid);
            initGame();
        }
    }

    /**
     * Initialise la partie une fois les 2 joueurs enregistrés, BOT ou non
     * @author Dorian Lacombe
     */
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
        if(j2.getPrenom().equals(" ") && j2.getNom().equals("BOT") && !isWhitePlaying) return;
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

        if(plateau.testEchecEtMat(isWhitePlaying) && !modeTournoi) {
            if(!isWhitePlaying) initEnd(j1, j2);
            else initEnd(j2, j1);
            return;
        } else if(plateau.testEchecEtMat(isWhitePlaying) && modeTournoi) {
            if(!isWhitePlaying) tournoiEndMsg(j1, j2);
            else tournoiEndMsg(j2, j1);
            return;
        }

        if(j2.getPrenom().equals(" ") && j2.getNom().equals("BOT") && !isWhitePlaying) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                piecesNoires = plateau.piecesNoires();
                ArrayList<Piece> toRemove = new ArrayList<>();
                ArrayList<ArrayList<int[]>> listMoves = new ArrayList<>();
                for (Piece p : piecesNoires) {
                    if(p.mouvementsPossibles(plateau).size() > 0) listMoves.addAll(Collections.singleton(p.mouvementsPossibles(plateau)));
                    else toRemove.add(p);
                }
                piecesNoires.removeAll(toRemove);
                Random random = new Random();
                int randomInt = random.nextInt((piecesNoires.size()));
                ArrayList<int[]> moveListChosen = listMoves.get(randomInt);
                int randomInt2 = random.nextInt((moveListChosen.size()));
                int[] moveChosen = moveListChosen.get(randomInt2);
                plateau.mouvement(piecesNoires.get(randomInt), moveChosen[0], moveChosen[1]);
                if(piecesNoires.get(randomInt) instanceof Pion) ((Pion) piecesNoires.get(randomInt)).setPremierCoup(false);
                isWhitePlaying = true;

                clearAll();
                displayGame(plateau);
            }));
            timeline.setCycleCount(1);
            timeline.play();
        }
        int sum = 0;
        for(Piece piece : plateau.piecesNoires()) {
            sum += piece.mouvementsPossibles(plateau).size();
        }
        if(sum == 0) {
            initPAT(j1, j2);
        }
        sum = 0;
        for(Piece piece : plateau.piecesBlanches()) {
            sum += piece.mouvementsPossibles(plateau).size();
        }
        if(sum == 0) {
            initPAT(j1, j2);
        }
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

    /**
     * Permet de gérer les timers des 2 joueurs en les faisant décrémenter toutes les secondes
     * @author Dorian Lacombe
     */
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
                        subTime1--;
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
                        initEnd(j2, j1);
                    } else if(time2 == 0 && subTime2 == 0 && !isWhitePlaying) {
                        initEnd(j1, j2);
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    /**
     * Permet de prendre en charge une fin de partie avec tout ce qui est nécessaire
     * @author Dorian Lacombe
     * @param winner Joueur gagnant
     * @param loser Joueur perdant
     */
    public void initEnd(Joueur winner, Joueur loser) {
        this.winner = winner;
        this.loser = loser;
        popupLabel.setText(winner.getPrenom() + " " + winner.getNom() + " a gagné la partie !");
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
        managerJoueur.modifieJoueurInformation(winner, winner.getNombrePartiesJouees()+1, winner.getNombrePartiesGagnees()+1);
        managerJoueur.modifieJoueurInformation(loser, loser.getNombrePartiesJouees()+1, loser.getNombrePartiesGagnees());
        ManagerParties managerParties = new ManagerParties();
        managerParties.ajouterPartie(winner, loser, false);
    }

    public void initPAT(Joueur winner, Joueur loser) {
        popupLabel.setText("PAT ! La partie est nulle !");
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
        managerJoueur.modifieJoueurInformation(winner, winner.getNombrePartiesJouees()+1, winner.getNombrePartiesGagnees());
        managerJoueur.modifieJoueurInformation(loser, loser.getNombrePartiesJouees()+1, loser.getNombrePartiesGagnees());
        ManagerParties managerParties = new ManagerParties();
        managerParties.ajouterPartie(winner, loser, true);
    }

    /**
     * Permet de fermer le popup indiquant la victoire
     * @author Dorian Lacombe
     */
    public void closePopup() {
        popup.getStyleClass().remove("visible");
        buttonPlay.setDisable(false);
        choiceBox.setDisable(false);
        buttonTournoi.setDisable(false);
        createBindings();
        j1 = null;
        j2 = null;
        nicknameMe.setText("Moi");
        nicknameEnnemy.setText("Adversaire");
    }

    /**
     * Permet de relancer automatiquement une partie avec les mêmes jours à partir du popup indiquant la victoire
     * @author Dorian Lacombe
     */
    public void rematch() {
        popup.getStyleClass().remove("visible");
        popupTournoi.setVisible(false);
        createBindings();
        play();
    }

    /**
     * Permet de prendre en charge le clic du bouton "Nouvelle partie" pour afficher le contenu associé
     * @author Dorian Lacombe
     */
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

    /**
     * Permet de prendre en charge le clic du bouton "Parties" pour afficher le contenu associé
     * @author Dorian Lacombe
     */
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
        loadGames();
    }

    /**
     * Permet de prendre en charge le clic du bouton "Joueurs" pour afficher le contenu associé
     * @author Dorian Lacombe
     */
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

    /**
     * Permet de charger la liste des joueurs enregistrés dans un fichier JSON et de les afficher
     * @author Dorian Lacombe
     */
    public void loadPlayers() {
        boxRight.getChildren().clear();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMinWidth(boxRight.getMinWidth());
        scrollPane.setMinHeight(boxRight.getMinHeight());
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        vBox.setMinHeight(scrollPane.getMinHeight());
        playersList = ManagerJoueur.getJoueurs().toArray(new Joueur[0]);
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
            vBox.getChildren().add(playerInList);
        }
        scrollPane.setContent(vBox);
        boxRight.getChildren().add(scrollPane);
        Region rg = new Region();
        VBox.setVgrow(rg, Priority.ALWAYS);
        boxRight.getChildren().add(rg);
    }

    /**
     * Permet de charger la liste des parties enregistrées dans un fichier JSON et de les afficher
     * @author Hugo Valente
     */
    public void loadGames() {
        boxRight.getChildren().clear();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMinWidth(boxRight.getMinWidth());
        scrollPane.setMinHeight(boxRight.getMinHeight());
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        vBox.setMinHeight(scrollPane.getMinHeight());
        gamesList = ManagerParties.getParties().toArray(new Partie[0]);
        for(Partie g : gamesList) {
            if(g == null) continue;
            HBox playerInList = new HBox();
            playerInList.getStyleClass().add("gameInList");
            Image img;
            if (g.isPAT()) {
                img = new Image("file:src/main/resources/img/balance.png");
            }else {
                img = new Image("file:src/main/resources/img/couronne.png");
            }
            ImageView imgV = new ImageView(img);
            imgV.setFitHeight(30);
            imgV.setFitWidth(30);
            Label winnerLabelPrenom = new Label(g.getJoueurGagnant().getPrenom());
            Label winnerLabelNom = new Label(g.getJoueurGagnant().getNom());
            Label separator = new Label("vs");
            Label loserLabelPrenom = new Label(g.getJoueurPerdant().getPrenom());
            Label loserLabelNom = new Label(g.getJoueurPerdant().getNom());
            winnerLabelPrenom.setAlignment(Pos.CENTER);
            winnerLabelNom.setAlignment(Pos.CENTER);
            separator.setAlignment(Pos.CENTER);
            loserLabelPrenom.setAlignment(Pos.CENTER);
            loserLabelNom.setAlignment(Pos.CENTER);
            Image image;
            if (g.isPAT()) {
                image = new Image("file:src/main/resources/img/balance.png");
            }else {
                image = new Image("file:src/main/resources/img/blesse.png");
            }
            ImageView imgV2 = new ImageView(image);
            imgV2.setFitHeight(30);
            imgV2.setFitWidth(30);
            playerInList.getChildren().addAll(imgV, winnerLabelPrenom, winnerLabelNom, separator, loserLabelPrenom, loserLabelNom, imgV2);
            vBox.getChildren().add(playerInList);
        }
        scrollPane.setContent(vBox);
        boxRight.getChildren().add(scrollPane);
        Region rg = new Region();
        VBox.setVgrow(rg, Priority.ALWAYS);
        boxRight.getChildren().add(rg);
    }

    /**
     * Permet d'initialiser la création d'un tournoi
     * @author Quentin Fournier
     * @param nombreJoueursTournoi Nombre de joueurs dans le tournoi
     * @param joueursTournoi Liste des joueurs participant
     */
    public void tournoi (int nombreJoueursTournoi, ArrayList<Joueur> joueursTournoi) {
        this.nombreJoueursTournoi = nombreJoueursTournoi;
        this.joueursTournoi = joueursTournoi;
        int index1 = dernierIndex;
        int index2 = dernierIndex+1;
        this.dernierIndex = index2;

        statsTournoi(joueursTournoi.size(), joueursTournoi);

        if (joueursTournoi.size()==1) {
            msgFinDeTournoi (joueursTournoi.get(0));
        }

        else {

            if (joueursTournoi.size()==2) {
                index1 = 0;
                index2 = 1;
            }

            indexList.add(index1);
            indexList.add(index2);

            this.j1 = joueursTournoi.get(index1);
            this.j1.setEstBlanc(true);
            this.j2 = joueursTournoi.get(index2);
            this.j2.setEstBlanc(false);
            this.isWhitePlaying = true;

            if (joueursTournoi.size()==2) {
                popupMatchLabel.setText("Finale : " + j1.getPrenom() + " " + j1.getNom() + " contre " + j2.getPrenom() + " " + j2.getNom());
            }
            else popupMatchLabel.setText(nbMatch + "e Partie : " + j1.getPrenom() + " " + j1.getNom() + " contre " + j2.getPrenom() + " " + j2.getNom());
            popupMatch.setVisible(true);
            popupMatch.getStyleClass().add("visible");
            popupMatchLabel.setWrapText(true);
            matchButton.onActionProperty().set(actionEvent -> {
                popupMatch.setVisible(false);
                modeTournoi = true;
                play();
            });
            nbMatch = nbMatch+1;
        }
    }

    /**
     * Permet de prendre en charge le clic sur le bouton "Tournois" pour créer les joueurs participant
     * @author Dorian Lacombe
     */
    public void tournoiBouton() {

        buttonPlay.setVisible(false);
        choiceBox.setVisible(false);
        buttonTournoi.setVisible(false);

        HBox hBoxAnnuler = new HBox();
        VBox vBoxTournoi = new VBox();
        this.vBoxTournoi = vBoxTournoi;
        VBox vBoxChamps = new VBox();
        HBox hBoxBoutons = new HBox();
        hBoxBoutons.getStyleClass().add("buttonsTournoi");

        Label choisirNbJoueurs = new Label("Nombre de joueurs :");
        choisirNbJoueurs.getStyleClass().add("choisirNbJoueurs");

        Button quatreJoueurs = new Button("4 joueurs");
        quatreJoueurs.onActionProperty().set(actionEvent -> {
            this.nbJoueursTextField = 4;
            this.nombreJoueursTournoi = 4;
            quatreJoueurs.getStyleClass().add("selectedButton");
            annonce.setVisible(false);
            choisirNbJoueurs.setDisable(true);
            tournoiJoueurs(nbJoueursTextField, hBoxBoutons, vBoxChamps, vBoxTournoi, hBoxAnnuler, choisirNbJoueurs);
        });

        Button huitJoueurs = new Button("8 joueurs");
        huitJoueurs.onActionProperty().set(actionEvent -> {
            this.nbJoueursTextField = 8;
            this.nombreJoueursTournoi = 8;
            choisirNbJoueurs.setDisable(true);
            huitJoueurs.getStyleClass().add("selectedButton");

            tournoiJoueurs(nbJoueursTextField, hBoxBoutons, vBoxChamps, vBoxTournoi, hBoxAnnuler, choisirNbJoueurs);
        });

        Button seizeJoueurs = new Button("16 joueurs");
        seizeJoueurs.onActionProperty().set(actionEvent -> {
            this.nbJoueursTextField = 16;
            this.nombreJoueursTournoi = 16;
            choisirNbJoueurs.setDisable(true);
            seizeJoueurs.getStyleClass().add("selectedButton");
            tournoiJoueurs(nbJoueursTextField, hBoxBoutons, vBoxChamps, vBoxTournoi, hBoxAnnuler, choisirNbJoueurs);
        });

        Button annuler = new Button("Annuler le tournoi");
        annuler.getStyleClass().add("annuler");
        annuler.setOnAction(actionEvent -> {
            boxRight.getChildren().remove(vBoxTournoi);
            buttonPlay.setVisible(true);
            choiceBox.setVisible(true);
            buttonTournoi.setVisible(true);
            annonce.setVisible(true);
        });

        hBoxBoutons.getChildren().addAll(quatreJoueurs, huitJoueurs, seizeJoueurs);
        hBoxBoutons.setAlignment(Pos.CENTER);

        vBoxTournoi.getChildren().addAll(choisirNbJoueurs, hBoxBoutons);
        vBoxTournoi.getChildren().add(vBoxChamps);

        vBoxTournoi.setAlignment(Pos.CENTER);
        vBoxTournoi.setSpacing(20);
        vBoxChamps.setSpacing(10);
        vBoxChamps.setAlignment(Pos.CENTER);

        hBoxAnnuler.getChildren().add(annuler);
        hBoxAnnuler.setAlignment(Pos.BOTTOM_CENTER);
        vBoxTournoi.getChildren().add(hBoxAnnuler);

        boxRight.getChildren().add(1, vBoxTournoi);
    }

    private void tournoiJoueurs(int nb, HBox hBoxBoutons, VBox vBoxChamps, VBox vBoxTournoi, HBox hBoxAnnuler, Label choisirNbJoueurs) {
        hBoxBoutons.setDisable(true);

        Label prenomTournoiLabel = new Label("Prenom : ");
        prenomTournoiLabel.getStyleClass().add("prenomLabel");
        TextField prenomTournoi = new TextField();
        prenomTournoi.getStyleClass().add("prenom");
        Label nomTournoiLabel = new Label("Nom : ");
        nomTournoiLabel.getStyleClass().add("nomLabel");
        TextField nomTournoi = new TextField();
        nomTournoi.getStyleClass().add("nom");
        Button validTournoi = new Button("Valider");
        validTournoi.getStyleClass().add("valid");

        vBoxChamps.getChildren().addAll(prenomTournoiLabel, prenomTournoi, nomTournoiLabel, nomTournoi, validTournoi);

        if (nb == 0) {
            vBoxTournoi.getChildren().remove(choisirNbJoueurs);
            vBoxChamps.getChildren().clear();
            hBoxBoutons.setVisible(false);
            Button demarrer = new Button("Demarrer");
            demarrer.getStyleClass().add("buttonPlay");
            demarrer.onActionProperty().set(actionEvent -> {
                vBoxTournoi.getChildren().clear();
                annonce.setVisible(true);
                tournoi(nombreJoueursTournoi, joueursTournoi);
            });
            vBoxTournoi.getChildren().add(0, demarrer);
        }

        validTournoi.onActionProperty().set(actionEvent -> {
            vBoxChamps.getChildren().clear();
            validButtonTournoi(nomTournoi.getText(), prenomTournoi.getText(), hBoxBoutons, vBoxChamps, vBoxTournoi, hBoxAnnuler, choisirNbJoueurs);
        });
    }

    private void validButtonTournoi(String nom, String prenom, HBox hBoxBoutons, VBox vBoxChamps, VBox vBoxTournoi, HBox hBoxAnnuler, Label choisirNbJoueurs) {
        joueursTournoi.add(new Joueur(nom, prenom));
        this.nbJoueursTextField = nbJoueursTextField - 1;
        tournoiJoueurs(nbJoueursTextField, hBoxBoutons, vBoxChamps, vBoxTournoi, hBoxAnnuler, choisirNbJoueurs);
    }


    /**
     * Permet de prendre en charge la fin d'une partie dans un tournoi pour afficher le gagnant et passer au prochain match
     * @author Quentin Fournier
     * @param winner Gagnant de la partie
     * @param loser Perdant de la partie
     */
    public void tournoiEndMsg (Joueur winner, Joueur loser) {
        joueursElimines.add(loser);
        joueursTournoi.remove(loser);
        joueursAyantJoue.add(winner);
        popupTournoiLabel.setText(winner.getPrenom() + " " + winner.getNom() + " a gagné la partie ! " + loser.getPrenom() + " " + loser.getNom() + " est éliminé du tournoi.");
        popupTournoi.setVisible(true);
        popupTournoi.getStyleClass().add("visible");
        popupTournoiLabel.setWrapText(true);
        popupSuivant.onActionProperty().set(actionEvent -> {
            popupTournoi.setVisible(false);
            tournoi(nombreJoueursTournoi, joueursTournoi);
        });
        createBindings();
        timer.cancel();
        timer.purge();
        chessBoard.setOnMouseClicked(null);
        if(nodeSelected != null)
            nodeSelected.getStyleClass().remove("selectedCell");
        clearMoves();
        cellSelected = null;
        nodeSelected = null;
        managerJoueur.modifieJoueurInformation(winner, winner.getNombrePartiesJouees()+1, winner.getNombrePartiesGagnees()+1);
        managerJoueur.modifieJoueurInformation(loser, loser.getNombrePartiesJouees()+1, loser.getNombrePartiesGagnees());

    }

    public void msgFinDeTournoi (Joueur winner) {
        this.nbMatch = 1;
        this.indexList = new ArrayList<>();
        this.joueursElimines = new ArrayList<>();
        this.dernierIndex = 0;
        this.joueursAyantJoue = new ArrayList<>();
        this.joueursTournoi = new ArrayList<>();
        this.nombreJoueursTournoi = 0;
        popupFinTournoiLabel.setText(winner.getPrenom() + " " + winner.getNom() + " a gagné le tournoi !");
        popupFinTournoi.setVisible(true);
        popupFinTournoi.getStyleClass().add("visible");
        popupFinTournoiLabel.setWrapText(true);
        popupFin.onActionProperty().set(actionEvent -> {
            vBoxTournoi.getChildren().clear();
            buttonPlay.setVisible(true);
            choiceBox.setVisible(true);
            buttonTournoi.setVisible(true);
            popupFinTournoi.setVisible(false);
            buttonPlay.setDisable(false);
            choiceBox.setDisable(false);
            buttonTournoi.setDisable(false);
            j1 = null;
            j2 = null;
            nicknameMe.setText("Moi");
            nicknameEnnemy.setText("Adversaire");
        });
    }

    public void statsTournoi(int nombreJoueursTournoi, ArrayList<Joueur> joueursTournoi) {
        vBoxTournoi.getChildren().clear();

        HBox hBoxLabels = new HBox();

        VBox vBoxJoueurs = new VBox();
        vBoxJoueurs.setSpacing(10);

        VBox vBoxJoueursElimines = new VBox();
        vBoxJoueursElimines.setSpacing(10);

        Image nonElimine = new Image("file:src/main/resources/img/validSized.png");
        Image elimine = new Image("file:src/main/resources/img/eliminatedSized.png");

        Label labelNbJoueurs = new Label(String.valueOf(nombreJoueursTournoi));
        labelNbJoueurs.getStyleClass().add("labelNbJoueurs");

        Label labelNbJoueursTexte = new Label(" joueurs encore en jeu");
        labelNbJoueursTexte.getStyleClass().add("labelNbJoueursTexte");

        Label labelJoueursFinal = new Label(labelNbJoueurs + " " + labelNbJoueursTexte);

        Label labelJoueurs = new Label("Joueurs :");
        labelJoueurs.getStyleClass().add("labelNbJoueursTexte");

        hBoxLabels.getChildren().addAll(labelNbJoueurs, labelNbJoueursTexte);
        hBoxLabels.setAlignment(Pos.CENTER);
        vBoxTournoi.getChildren().addAll(hBoxLabels, labelJoueurs);

        for (Joueur joueur : joueursTournoi) {
            HBox hBoxJoueur = new HBox();
            hBoxJoueur.setSpacing(10);

            ImageView imageView = new ImageView(nonElimine);
            Label labelJoueur = new Label(joueur.getPrenom() + " " + joueur.getNom());
            labelJoueur.getStyleClass().add("labelNbJoueurs");

            hBoxJoueur.getChildren().addAll(imageView, labelJoueur);
            vBoxJoueurs.getChildren().add(hBoxJoueur);
        }
        vBoxTournoi.getChildren().add(vBoxJoueurs);

        for (Joueur joueur : joueursElimines) {
            HBox hBoxJoueurElimine = new HBox();
            hBoxJoueurElimine.setSpacing(10);

            ImageView imageView = new ImageView(elimine);
            Label labelJoueur = new Label(joueur.getPrenom() + " " + joueur.getNom());
            labelJoueur.getStyleClass().add("joueurElimine");

            hBoxJoueurElimine.getChildren().addAll(imageView, labelJoueur);
            vBoxJoueursElimines.getChildren().add(hBoxJoueurElimine);
        }

        vBoxTournoi.getChildren().add(vBoxJoueursElimines);
        vBoxTournoi.setAlignment(Pos.CENTER);
    }

}

