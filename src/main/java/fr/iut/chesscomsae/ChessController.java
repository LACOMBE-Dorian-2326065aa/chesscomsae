package fr.iut.chesscomsae;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class ChessController implements Initializable {

    @FXML
    private ImageView userImageView;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createChessBoard();
        choiceBox.getSelectionModel().select("10 min");
        labelPlaying.setText(Integer.toString(ThreadLocalRandom.current().nextInt(100000, 200001)));
        labelGames.setText(Integer.toString(ThreadLocalRandom.current().nextInt(13000000, 14000001)));
    }

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

    public void play() {
        String timerString = choiceBox.getSelectionModel().getSelectedItem();
        timerString = timerString.replace(" min", ":00").replace("5", "05");
        timerMe.setText(timerString);
        timerEnnemy.setText(timerString);
        buttonPlay.setDisable(true);
    }
}
