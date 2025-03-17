package org.example;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;

import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.IOException;
public class Verse extends Application {
    @FXML
    private Button regenerate;

    @FXML
    private Text bibleText;



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root=FXMLLoader.load(getClass().getResource("/Verse.fxml"));
        stage.setTitle("Verse of the day");
        stage.setScene(new Scene(root,600,400));
        stage.show();

    }

    @FXML
    public void setBibleText(ActionEvent event) throws IOException {
        bibleText.setText(Main.getRandomVerse());

    }

    @FXML
    public void setRegenerate(ActionEvent event) throws IOException {
        setBibleText(event);
    }

}
