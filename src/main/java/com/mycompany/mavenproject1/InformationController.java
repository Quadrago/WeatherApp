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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class InformationController implements Initializable {

    private LocationRequester locationRequester;

    @FXML
    private ComboBox<String> CountryBox;

    @FXML
    private ComboBox<String> stateBox;

    @FXML
    private ComboBox<String> cityBox;

    @FXML
    private TextField nameInput;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stateBox.setDisable(true);
        cityBox.setDisable(true);
        locationRequester = new LocationRequester();
        String[] countries = locationRequester.getCountries();

        CountryBox.getItems().addAll(countries);

    }

    @FXML
    void loadStates(ActionEvent event) {
        String[] states = locationRequester.getStates(CountryBox.getValue());
        stateBox.getItems().setAll(states);
        stateBox.setDisable(false);
        cityBox.setDisable(true);
        cityBox.getSelectionModel().clearSelection();
        stateBox.getSelectionModel().clearSelection();
    }

    @FXML
    void loadCities(ActionEvent event) {
        String[] cities = locationRequester.getCities(CountryBox.getValue(), stateBox.getValue());
        cityBox.getItems().setAll(cities);
        cityBox.setDisable(false);
        cityBox.getSelectionModel().clearSelection();
    }

    @FXML
    void getWeather(MouseEvent event) throws IOException {


        String name = nameInput.getText();

        if(name.isBlank()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Missing information");
            alert.setContentText("Please input your name into the Name text field");
            alert.showAndWait();
            return;
        }
        else if(CountryBox.getValue()==null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Missing information");
            alert.setContentText("Please select a country on the drop down menu");
            alert.showAndWait();
            return;
        }
        else if(stateBox.getValue()==null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Missing information");
            alert.setContentText("Please select a state on the drop down menu");
            alert.showAndWait();
            return;
        }
        else if(cityBox.getValue()==null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Missing information");
            alert.setContentText("Please select a city on the drop down menu");
            alert.showAndWait();
            return;
        }
        
        String[] locationArr = {CountryBox.getValue(),stateBox.getValue(),cityBox.getValue()};

        AccuWeather a = new AccuWeather();
        String[] weatherData = null;
        weatherData = a.requestLocation(CountryBox.getValue(), stateBox.getValue(), cityBox.getValue());
        if(weatherData==null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("No Location Found");
            alert.setContentText("Please select another location");
            alert.showAndWait();
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("WeatherData.fxml"));
        Parent root = loader.load();

        // pass weather data to clothing recommendation controller
        WeatherDataController weatherDataController = loader.getController();
        weatherDataController.setInfo(weatherData, locationArr, name);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);;
        stage.show();
    }


    
    

}
