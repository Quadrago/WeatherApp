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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WeatherDataController implements Initializable {
    @FXML
    private Text humidityLabel;

    @FXML
    private Text locationLabel;

    @FXML
    private Text temperatureLabel;

    @FXML
    private Text windSpeedLabel;

    
    private String[] weatherInfo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setInfo(String[] weatherInfo, String place) {

        this.weatherInfo = weatherInfo;
        locationLabel.setText("The Weather Data in " + place);
        temperatureLabel.setText("Temperature: " + weatherInfo[0]);
        windSpeedLabel.setText("Wind: " + weatherInfo[1]);
        humidityLabel.setText("Humidity: " + weatherInfo[2]);

    }
    @FXML
    void switchToClothingRec(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RecommendClothing.fxml"));
        Parent root = loader.load();

        //pass weather data to clothing recommendation controller
        ClothingController tempController = loader.getController();
        String[] temp = {"1","1","1"};
        tempController.setInfo(temp,"jordan");

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);;
        stage.show();
    }
    
    
}
