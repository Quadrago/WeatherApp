package com.mycompany.mavenproject1;
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
     * Uses Jsoup to connect to site and return relavent weather data
     * @param country the country chosen by the user
     * @param state the state chosen by the user
     * @param city the city chosen by the user
     * @return Array containg temperature, wind and humidity data
     */
    public String[] requestLocation(String country, String state, String city) {

        //Document to parse accu weather data
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.accuweather.com/en/search-locations?query="+removeKeyWords(city)+"+"+removeKeyWords(state)+"+"+removeKeyWords(country)).get();
        } catch (IOException e) {
        }

        //selects the element containing a link to a more detailed info site
        Element test = doc.select("a.cur-con-weather-card").first();;

        //new link for more specific weather data
        String newLink =null;
        try{
            newLink =  test.attr("href");
        } catch (Exception e) {
            return null;
        }
        Document info = null;
        try {
            info = Jsoup.connect("https://www.accuweather.com"+newLink).get();
        } catch (IOException e) {
        }

        //selects all the relavent weather details 
        Elements weatherElements = info.select("div.current-weather-details > div");
       
        //selects certain elements from the weather details
        String temp = weatherElements.get(0).lastElementChild().text();
        String wind = weatherElements.get(1).lastElementChild().text();
        String humidity = weatherElements.get(3).lastElementChild().text();
        
        //stores the weather data into an array
        String[] weatherData = {temp, wind, humidity};

        return weatherData;
    }
}