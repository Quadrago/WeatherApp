package com.mycompany.mavenproject1;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AccuWeather {

    private String[] keyWords = {"Region", "District","County", "Town", "Province","Department","Prefecture"};
    private String removeKeyWords(String location) {
        for(String key: keyWords) {
            location = location.replace(key,"");
        }
        return location.strip();
    }
    
    public String[] requestLocation(String country, String state, String city) {

        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.accuweather.com/en/search-locations?query="+removeKeyWords(city)+"+"+removeKeyWords(state)+"+"+removeKeyWords(country)).get();
        } catch (IOException e) {
        }

        Element test = doc.select("a.cur-con-weather-card").first();;
        String newLink =null;
        try{
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

        Elements weatherElements = info.select("div.current-weather-details > div");
       
        String temp = weatherElements.get(0).lastElementChild().text();
        String wind = weatherElements.get(1).lastElementChild().text();
        String humidity = weatherElements.get(3).lastElementChild().text();
        
        String[] weatherData = {temp, wind, humidity};
        return weatherData;
    }
   
}