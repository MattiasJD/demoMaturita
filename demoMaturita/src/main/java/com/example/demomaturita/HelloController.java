package com.example.demomaturita;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;

public class HelloController {
    public static Label bigLabel;
    @FXML
    private Label welcomeText;

    public void onLoadButtonClick(ActionEvent actionEvent) {
        try {
            HelloApplication.openFileChooser();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}