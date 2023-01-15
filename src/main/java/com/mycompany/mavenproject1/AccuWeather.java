package com.mycompany.mavenproject1;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AccuWeather {
    public AccuWeather() {
        System.out.println("Instantiated accuweather class!");
    }
    
    public String[] requestLocation(String country, String state, String city) throws IOException {

        Document doc = Jsoup.connect("https://www.accuweather.com/en/search-locations?query="+city+"+"+country+"+"+state).get();

       
        Element test = doc.select("a.cur-con-weather-card").first();
        String newLink = test.attr("href");
        System.out.println(newLink);
        Document info = Jsoup.connect("https://www.accuweather.com"+newLink).get();

        Elements weatherElements = info.select("div.current-weather-details > div");
        // for(Element tempInfo: weatherInfo) {
        //     System.out.println(tempInfo.lastElementChild().text());
        // }
        String temp = weatherElements.get(0).lastElementChild().text();
        String wind = weatherElements.get(1).lastElementChild().text();
        String humidity = weatherElements.get(3).lastElementChild().text();
        
        String[] weatherData = {temp, wind, humidity};
        return weatherData;
    }
   
}