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
    public String requestLocation(String country, String state, String city) throws IOException {

        Document doc = Jsoup.connect("https://www.accuweather.com/en/search-locations?query="+city+"+"+country+"+"+state).get();

       
        Element test = doc.select("a.cur-con-weather-card").first();
        String newLink = test.attr("href");
        System.out.println(newLink);
        Document info = Jsoup.connect("https://www.accuweather.com"+newLink).get();

        Elements weatherInfo = info.select("div.current-weather-details > div");
        for(Element tempInfo: weatherInfo) {
            System.out.println(tempInfo.lastElementChild().text());
        }


        return "";
    }
    public double getTemperature(String location) {
        return -1.0;
    }
    public double getWindSpeed(String location) {
        return -1.0;
    }
    public double getHumidity(String location) {
        return -1.0;
    }
}