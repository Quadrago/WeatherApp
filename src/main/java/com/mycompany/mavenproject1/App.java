package com.mycompany.mavenproject1;
//Importing necessary elements
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    /**
     * Sets up the start GUI interface
     * @param stage the GUI interface 
     */
    public void start(Stage stage) throws IOException {
        //Sets the first page of the GUI
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        //Sets the title of the stage
        stage.setTitle("Clothing Recommender");
        //Sets the scene
        stage.setScene(new Scene(root));
        //Shows the stage
        stage.show();
        
    }

    public static void main(String[] args) {
        launch();
    }
}