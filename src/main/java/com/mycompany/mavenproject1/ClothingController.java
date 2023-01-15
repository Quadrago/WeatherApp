package com.mycompany.mavenproject1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ClothingController implements Initializable {
    
    @FXML
    private ImageView fAccessoryView;

    @FXML
    private ImageView fBottomView;

    @FXML
    private ImageView fShoeView;

    @FXML
    private ImageView fTopView;
    
    @FXML
    private ImageView mAccessoryView;
    
    @FXML
    private ImageView mBottomView;
    
    @FXML
    private ImageView mShoeView;
    
    @FXML
    private ImageView mTopView;
    
    @FXML
    private Text locationLabel;

    private String wind;
    private double temp;
    private double humidity;
    private String[] weatherInfo;

    private String hotWear = "hotWear\\";
    private String warmWear = "warmWear\\";
    private String averageWear = "averageWear\\";
    private String coldWear = "coldWear\\";
    
    @FXML
    void switchToClosingMenu(ActionEvent event) {
        
    }
    @FXML
    void switchToWeather(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WeatherData.fxml"));
        Parent root = loader.load();

        //pass weather data to clothing recommendation controller
        WeatherDataController tempController = loader.getController();
        tempController.setInfo(weatherInfo,"jordan");

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);;
        stage.show();
    }

    private String imageDir = "WeatherApp\\src\\main\\resources\\com\\mycompany\\mavenproject1\\images\\";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("test");
    
    }
    private Image getImage(String file) throws FileNotFoundException {
   
        InputStream stream = new FileInputStream(imageDir + file);
        Image image = new Image(stream);
        return image;

    }
    private void drawHotClothes() throws FileNotFoundException {
        
        mTopView.setImage(getImage(hotWear+"tshirt.jpg"));
        mBottomView.setImage(getImage(hotWear+"shorts.jpg"));
        mShoeView.setImage(getImage(hotWear+"flip flops.jpg"));
        mAccessoryView.setImage(getImage(hotWear+"sun glasses.jpg"));
        
        fTopView.setImage(getImage(hotWear + "tanktop.jpg"));
        fBottomView.setImage(getImage(hotWear + "skirt.jpg"));
        fShoeView.setImage(getImage(hotWear + "crocs.jpg"));
        fAccessoryView.setImage(getImage(hotWear + "sun hat.jpg"));
    }
        
    private void drawWarmClothes() throws FileNotFoundException {
        
        mTopView.setImage(getImage(warmWear+"button up shirt.jpg"));
        mBottomView.setImage(getImage(warmWear+"trousers.jpg"));
        mShoeView.setImage(getImage(warmWear+"running shoes.jpg"));
        mAccessoryView.setImage(getImage(warmWear+"baseball cap.jpg"));
        
        fTopView.setImage(getImage(warmWear + "blouse.jpg"));
        fBottomView.setImage(getImage(warmWear + "leggings.jpg"));
        fShoeView.setImage(getImage(warmWear + "sandals.jpg"));
        fAccessoryView.setImage(getImage(warmWear + "bucket hat.jpg"));
    }
    private void drawAverageClothes() throws FileNotFoundException {
        
        mTopView.setImage(getImage(averageWear+"turtleneck.jpg"));
        mBottomView.setImage(getImage(averageWear+"jeans.jpg"));
        mShoeView.setImage(getImage(averageWear+"timberland Boots.jpg"));
        mAccessoryView.setImage(getImage(averageWear+"windbreaker.jpg"));
        
        fTopView.setImage(getImage(averageWear + "cardigan.jpg"));
        fBottomView.setImage(getImage(averageWear + "jeggings.jpg"));
        fShoeView.setImage(getImage(averageWear + "uggs.jpg"));
        fAccessoryView.setImage(getImage(averageWear + "scarf.jpg"));
    }
    private void drawColdClothes() throws FileNotFoundException {
        
        mTopView.setImage(getImage(coldWear+"sweater.jpg"));
        mBottomView.setImage(getImage(coldWear+"pants.jpg"));
        mShoeView.setImage(getImage(coldWear+"winter boots.jpg"));
        mAccessoryView.setImage(getImage(coldWear+"toque.jpg"));
        
        fTopView.setImage(getImage(coldWear + "hoodie.jpg"));
        fBottomView.setImage(getImage(coldWear + "sweat pants.jpg"));
        fShoeView.setImage(getImage(coldWear + "knee high boots.jpg"));
        fAccessoryView.setImage(getImage(coldWear + "jacket.jpg"));
    }

    public void setInfo(String[] weatherInfo, String place) throws FileNotFoundException {
        // temp = Double.parseDouble(weatherInfo[0]);
        this.weatherInfo = weatherInfo;
        String tempString = weatherInfo[0];
        temp = Double.parseDouble(tempString.substring(0,tempString.length()-1));
        

        locationLabel.setText("The Weather Data in " + place);

        if(temp  >= 20) {
            drawHotClothes();
        }
        else if(temp >= 10) {
            drawWarmClothes();
        }
        else if(temp >= 0) {
            drawAverageClothes();
        }
        else if(temp < 0) {
            drawColdClothes(); 
        }

    }
    
}
