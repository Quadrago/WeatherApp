package com.mycompany.mavenproject1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

public class InformationController implements Initializable {

    private LocationRequester locationRequester;

    @FXML
    private ComboBox<String> CountryBox;

    @FXML
    private ComboBox<String> stateBox;

    @FXML
    private ComboBox<String> cityBox;

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
        AccuWeather a = new AccuWeather();
        a.requestLocation(CountryBox.getValue(), stateBox.getValue(), cityBox.getValue());
    }
    
    

}
