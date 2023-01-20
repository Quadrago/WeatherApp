package com.mycompany.mavenproject1;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

/**
 * Controllr for the menu screen
 */
public class ClosingMenuController {
    
    @FXML
    /**
     * Sends the user back to the Main Menu for use
     * @param event the action performed when clicking the button
     * @throws IOException
     */
    void backToMenu(ActionEvent event) throws IOException {
        //Sets the root to the Main Menu screen
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        //Establishes the stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //Refers the new scene to the root
        Scene scene = new Scene(root);
        //Sets the scene
        stage.setScene(scene);
        //Shows the stage
        stage.show();
    }

    @FXML
    /**
     * Closes the screen
     * @param event the action performed when clicking the button
     */
    void closeScreen(ActionEvent event) {
        //Exits the platform in use
        Platform.exit();
        //Ends the program
        System.exit(0);
    }
}