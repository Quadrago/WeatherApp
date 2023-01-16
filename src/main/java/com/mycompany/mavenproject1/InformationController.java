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
        stateBox.setDisable(false);
        cityBox.setDisable(true);
        System.out.println("Clearing box done");
        String[] states = locationRequester.getStates(CountryBox.getValue());
        stateBox.getItems().setAll(states);
    }

    @FXML
    void loadCities(ActionEvent event) {
        cityBox.setDisable(false);
        String[] cities = locationRequester.getCities(CountryBox.getValue(), stateBox.getValue());
        cityBox.getItems().setAll(cities);
    }

    @FXML
    void getWeather(MouseEvent event) throws IOException {

        String name = nameInput.getText();
        String[] locationArr = {CountryBox.getValue(),stateBox.getValue(),cityBox.getValue()};

        AccuWeather a = new AccuWeather();
        String[] weatherData = a.requestLocation(CountryBox.getValue(), stateBox.getValue(), cityBox.getValue());
        // String[] weatherData = a.requestLocation("Canada", "Ontario", "Markham");
        for(String s : weatherData) System.out.println(s);
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
