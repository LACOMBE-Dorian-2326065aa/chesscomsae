package fr.iut.chesscomsae;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class ChessController implements Initializable {

    @FXML
    private ImageView userImageView;
    @FXML
    private GridPane chessBoard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createChessBoard();
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
}
