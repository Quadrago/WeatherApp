package com.mycompany.mavenproject1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainMenuController implements Initializable {

    @FXML
    private void switchToInfo(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GeneralInformation.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);;
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
}
