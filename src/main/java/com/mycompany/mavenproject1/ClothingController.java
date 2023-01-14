package com.mycompany.mavenproject1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ClothingController implements Initializable {

    private String wind;
    private double temp;
    private double humidity;


    @FXML
    private Text locationLabel;

    @FXML
    private ImageView imageTest;


    @FXML
    void switchToClosingMenu(ActionEvent event) {

    }

    private String imageDir = "WeatherApp\\src\\main\\resources\\com\\mycompany\\mavenproject1\\images\\";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(System.getProperty("user.dir"));
        
        try (InputStream stream = new FileInputStream(imageDir+"b.jpeg")) {
            Image image = new Image(stream);
            
            imageTest.setImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setInfo(String[] weatherInfo, String place) {

        temp = Double.parseDouble(weatherInfo[0]);
        wind = weatherInfo[1];
        humidity = Double.parseDouble(weatherInfo[2]);

        locationLabel.setText("The Weather Data in " + place);

    }
    
}
