package com.mycompany.mavenproject1;

//Importing necessary elements
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Controller for the clothing recommmender screen
 */
public class ClothingController {
    
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

    //Declares the variable
    private String wind;
    private double temp;
    private double humidity;
    private String[] weatherInfo;
    
    //directories for each clothing option
    private String hotWear = "hotWear\\";
    private String warmWear = "warmWear\\";
    private String averageWear = "averageWear\\";
    private String coldWear = "coldWear\\";

    //Different clothing options for different weather ranges
    private String[] aboveTwenty = {"T-shirt", "Shorts", "Flip Flops", "Sunglasses", "Tanktop", "Skirt", "Crocs", "Sun Hat"};
    private String[] aboveTen = {"Button Up Shirt", "Trousers", "Running Shoes", "Baseball Cap", "Blouse", "Leggings", "Sandals", "Bucket Hat"};
    private String[] aboveZero = {"Turtleneck", "Jeans", "Timberland Boots", "Windbreaker", "Cardigan", "Jeggings", "Uggs", "Scarf"};
    private String[] belowZero = {"Sweaters", "Pants", "Winter Boots", "Toque", "Hoodies", "Sweat Pants", "Knee-High Boots", "Jacket"};
    
    private  String[] locationArr;
    private String name;
    

    @FXML
    /**
     * Switches to the Clothing Menu when the event occurs
     * @param event action performed when clicking a button
     * @throws IOException
     */
    void switchToClosingMenu(ActionEvent event) throws IOException {
        //Sets the format of the date 
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        Date date = new Date(); 
        String strDate = formatter.format(date); 

        //If else statements to determine the correct clothing recommendation 
        if(temp >= 20) {
            CSV.exportData(name,locationArr, strDate, weatherInfo, aboveTwenty);
        }
        else if (temp >= 10) {
            CSV.exportData(name,locationArr, strDate, weatherInfo, aboveTen);
        }
        else if (temp >= 0) {
            CSV.exportData(name,locationArr, strDate, weatherInfo, aboveZero);
        }
        else if (temp < 0){
            CSV.exportData(name,locationArr, strDate, weatherInfo, belowZero);
        }

        //Loads the Closing Menu screen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ClosingMenu.fxml"));
        Parent root = loader.load();
        //uses current stage of screen as the new stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    /**
     * Switches to the Weather Data screen
     * @param event the action performed when clicking the button
     * @throws IOException
     */
    void switchToWeather(ActionEvent event) throws IOException {
        //Establishes the loader to be the Weather Data screen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WeatherData.fxml"));
        Parent root = loader.load();

        //Passes the weather data to the clothing recommendation controller
        WeatherDataController tempController = loader.getController();
        tempController.setInfo(weatherInfo, locationArr, name);

        //loads the new screen
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    //the image directory
    private String imageDir = "src\\main\\resources\\com\\mycompany\\mavenproject1\\images\\";

    /**
     * Gets the needed image
     * @param file refers to the file of the image
     * @return returns the image needed
     * @throws FileNotFoundException
     */
    private Image getImage(String file) throws FileNotFoundException {
        InputStream stream = new FileInputStream(imageDir + file);
        Image image = new Image(stream);
        return image;
    }

    //draws the needed recommended clothing images for the hot weather
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

    //draws the needed recommended clothing images for the warm weather
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

    //draws the needed recommended clothing images for the average weather
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

    //draws the needed recommended clothing images for the cold weather
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

    /**
     * Sets the information for the next screen
     * @param weatherInfo the weather data scraped
     * @param locationArr the location entered by the user
     * @param name the name of the user
     * @throws FileNotFoundException
     */
    public void setInfo(String[] weatherInfo, String[] locationArr, String name) throws FileNotFoundException {
        this.weatherInfo = weatherInfo;
        this.locationArr = locationArr;
        this.name = name;

        //gets number from temperature data and removes the symbols
        String tempString = weatherInfo[0];
        temp = Double.parseDouble(tempString.substring(0,tempString.length()-1));
        
        //Displays the weather data to the user
        locationLabel.setText("The Weather Data in " + locationArr[2] +", " + locationArr[1] + " " + locationArr[0]);

        // If and else statements to determine which images will be drawn
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