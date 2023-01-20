package com.mycompany.mavenproject1;
//Importing necessary elements
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuController   {

    @FXML
    /**
     * Sends the user to the Gerneral Information menu
     * @param event the action performed when clicking the button
     * @throws IOException
     */
    private void switchToInfo(ActionEvent event) throws IOException {
        //Sets the root for the General Information Menu
        Parent root = FXMLLoader.load(getClass().getResource("GeneralInformation.fxml"));
        //Establishes the stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        //sends user to new scene
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}