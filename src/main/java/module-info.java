module fr.iut.chesscomsae {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens fr.iut.chesscomsae to javafx.fxml;
    exports fr.iut.chesscomsae;
}