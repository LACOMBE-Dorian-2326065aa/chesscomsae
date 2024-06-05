module fr.iut.chesscomsae {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens fr.iut.chesscomsae to javafx.fxml;
    exports fr.iut.chesscomsae;
}