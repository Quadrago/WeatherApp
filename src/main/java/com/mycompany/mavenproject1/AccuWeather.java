package com.mycompany.mavenproject1;
//Importing necessary elements
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AccuWeather {
    //Key words to be removed
    private String[] keyWords = {"Region", "District","County", "Town", "Province","Department","Prefecture"};
    
    /**
     * Removes key words
     * @param location string country, state, or city
     * @return cleaned string location
     */
    private String removeKeyWords(String location) {
        for(String key: keyWords) {
            //Replaces key words with empty space
            location = location.replace(key,"");
        }
        return location.strip();
    }
    
    /**
     * 
     * @param country the country chosen by the user
     * @param state the state chosen by the user
     * @param city the city chosen by the user
     * @return the scraper weather data
     */
    public String[] requestLocation(String country, String state, String city) {

        //Establishes the Java Doc
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.accuweather.com/en/search-locations?query="+removeKeyWords(city)+"+"+removeKeyWords(state)+"+"+removeKeyWords(country)).get();
        } catch (IOException e) {
        }

        Element test = doc.select("a.cur-con-weather-card").first();;
        String newLink =null;
        try{
            //Establishes the Attribute Key
            newLink =  test.attr("href");
        } catch (Exception e) {
            return null;
        }
        System.out.println(newLink);
        Document info = null;
        try {
            info = Jsoup.connect("https://www.accuweather.com"+newLink).get();
        } catch (IOException e) {
        }

        //Establishes the values for the different weather elements
        Elements weatherElements = info.select("div.current-weather-details > div");
       
        //Puts the weather data onto the GUI
        String temp = weatherElements.get(0).lastElementChild().text();
        String wind = weatherElements.get(1).lastElementChild().text();
        String humidity = weatherElements.get(3).lastElementChild().text();
        
        //Uploads the weather data into an array
        String[] weatherData = {temp, wind, humidity};
        //Returns the scraped weather data
        return weatherData;
    }
}