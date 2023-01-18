package com.mycompany.mavenproject1;
//Importing necessary elements
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WeatherDataController {
    //Establishes the different labels
    @FXML
    private Text humidityLabel;

    @FXML
    private Text locationLabel;

    @FXML
    private Text temperatureLabel;

    @FXML
    private Text windSpeedLabel;

    //Establishes needed arrays for the weather data, location, and the name
    private String[] weatherInfo;
    private String[] locationArr;
    private String name;

    /**
     * Sets the information
     * @param weatherInfo the weather data
     * @param locationArr the location array
     * @param name the name entered by the user
     */
    public void setInfo(String[] weatherInfo, String[] locationArr, String name) {
        //Refers to the location array
        this.locationArr = locationArr;
        //Refers to the name entered by the user
        this.name = name;
        //Refers to the weather information array
        this.weatherInfo = weatherInfo;

        //Establishes the labels to tell the user the scraped information
        locationLabel.setText("The Weather Data in " + locationArr[2] +", " + locationArr[1] + " " + locationArr[0]);
        temperatureLabel.setText("Temperature: " + weatherInfo[0]);
        windSpeedLabel.setText("Wind: " + weatherInfo[1]);
        humidityLabel.setText("Humidity: " + weatherInfo[2]);
    }

    @FXML
    /**
     * Switches to the Clothing Recommendation screen
     * @param event the action performed when clicking the button
     * @throws IOException
     */
    void switchToClothingRec(ActionEvent event) throws IOException {
        //Establishes the loader to the Clothing Recommendation screen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RecommendClothing.fxml"));
        //Establishes the root
        Parent root = loader.load();

        //Passes the weather data to the clothing recommendation controller
        ClothingController tempController = loader.getController();
        //Sets the information to the controller
        tempController.setInfo(weatherInfo, locationArr,name);

        //Establishes the stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //Establishes the scene with the root
        Scene scene = new Scene(root);
        //Sets the scene
        stage.setScene(scene);
        //Sets the stage
        stage.show();
    }

    @FXML
    /**
     * Switches the information to the next screen
     * @param event the action performed when clicking the button
     * @throws IOException
     */
    void switchToInfo(ActionEvent event) throws IOException {
        //Establishes the loader to the General Information screen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GeneralInformation.fxml"));
        //Establishes the root
        Parent root = loader.load();

        //Establishes the stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //Establishes the scene with the root
        Scene scene = new Scene(root);
        //Sets the scene
        stage.setScene(scene);
        //Shows the stage
        stage.show();
    }
}