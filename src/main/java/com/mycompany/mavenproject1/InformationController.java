package com.mycompany.mavenproject1;
//Importing necessary elements
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

        //loads countries into combobox
        String[] countries = locationRequester.getCountries();
        CountryBox.getItems().addAll(countries);

    }

    
    @FXML
    /**
     * loads states based on selected country
     * @param event
     */
    void loadStates(ActionEvent event) {
        String[] states = locationRequester.getStates(CountryBox.getValue());

        //initalize combobox full of items
        stateBox.getItems().setAll(states);

        //changes ability to use specific combo boxes 
        stateBox.setDisable(false);
        cityBox.setDisable(true);

        //clears selection for next boxes
        cityBox.getSelectionModel().clearSelection();
        stateBox.getSelectionModel().clearSelection();
    }

    @FXML
    /**
     * loads cities based on selected state
     * @param event when clicking on an item for the state box
     */
    void loadCities(ActionEvent event) {
        String[] cities = locationRequester.getCities(CountryBox.getValue(), stateBox.getValue());
        
        //puts all cities in city combobox
        cityBox.getItems().setAll(cities);

        cityBox.setDisable(false);
        cityBox.getSelectionModel().clearSelection();
    }

    @FXML
    /**
     * goes through many checks to see if all data is filled out to continue to next screen
     * @param event clicked on "continue" button
     * @throws IOException
     */
    void getWeather(MouseEvent event) throws IOException {


        String name = nameInput.getText();
        //no name
        if(name.isBlank()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Missing information");
            alert.setContentText("Please input your name into the Name text field");
            alert.showAndWait();
            return;
        }
        //no country picked
        else if(CountryBox.getValue()==null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Missing information");
            alert.setContentText("Please select a country on the drop down menu");
            alert.showAndWait();
            return;
        }
        //no state picked
        else if(stateBox.getValue()==null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Missing information");
            alert.setContentText("Please select a state on the drop down menu");
            alert.showAndWait();
            return;
        }
        //no city picked
        else if(cityBox.getValue()==null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Missing information");
            alert.setContentText("Please select a city on the drop down menu");
            alert.showAndWait();
            return;
        }
        
        //creates array of location data
        String[] locationArr = {CountryBox.getValue(),stateBox.getValue(),cityBox.getValue()};

        //gets weather data from requested location
        AccuWeather a = new AccuWeather();
        String[] weatherData = null;
        weatherData = a.requestLocation(CountryBox.getValue(), stateBox.getValue(), cityBox.getValue());
        
        //return error if there is no weather data
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

        //sets new scene for next page
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    /**
     * switches back to menu screen
     * @param event click on "back" button
     * @throws IOException
     */
    void switchToMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);;
        stage.show();
    }


    
    

}
