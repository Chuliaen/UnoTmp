package org.unotmp.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainController {
    @FXML
    private AnchorPane rootPane;

    @FXML
    protected void onStartButtonClick() {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("BoardLobby.fxml"));
            rootPane.getChildren().setAll(pane);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
