package com.mycompany.mavenproject1;
//Importing necessary elements
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;

/**
 * using the referential api, it reqests location data given specific parameters
 */
public class LocationRequester {
   
    //maps each country and state to their short forms
    private Map<String,String> countryMap;
    private Map<String,String> stateMap;
    
    /**
     * @return an array of countries
     */
    public String[] getCountries()  {
        System.out.println("Getting Countries.....");

        //request form
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://referential.p.rapidapi.com/v1/country?limit=250"))
            .header("X-RapidAPI-Key", "80cfee487dmsh3c85dbf436bac52p19a664jsnab7a8bdfb0ad")
            .header("X-RapidAPI-Host", "referential.p.rapidapi.com")
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
        
        //request from site and stores into response
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        
        //parses the body into a json array
        JSONArray arr = new JSONArray(response.body());
        countryMap = new HashMap<String,String>();

        //maps each country is json array to their respective alpha-2 code
        for(int i = 0; i < arr.length(); i++){
            countryMap.put(arr.getJSONObject(i).getString("value"), arr.getJSONObject(i).getString("key")); //Value is the key for country
        }

        String[] countries = countryMap.keySet().toArray(new String[0]);
        Arrays.sort(countries);

        return countries;
    }
    
    /**
     * @param country full country name 
     * @return array of states based on country input
     */
    public String[] getStates(String country) {
        
        //request form
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://referential.p.rapidapi.com/v1/state?iso_a2="+countryMap.get(country)+"&limit=250"))
        .header("X-RapidAPI-Key", "80cfee487dmsh3c85dbf436bac52p19a664jsnab7a8bdfb0ad")
        .header("X-RapidAPI-Host", "referential.p.rapidapi.com")
        .method("GET", HttpRequest.BodyPublishers.noBody())
        .build();

        //request from site and stores into response
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

        //parses the body into a json array
        JSONArray arr = new JSONArray(response.body());
        stateMap = new HashMap<String,String>();

        //maps each state to their respective state codes
        for(int i = 0; i < arr.length(); i++){
            stateMap.put(arr.getJSONObject(i).getString("value"), arr.getJSONObject(i).getString("key")); //Value is the key for country
        }
        String[] states = stateMap.keySet().toArray(new String[0]);
        Arrays.sort(states);

        return states;
    }
    public String[] getCities(String country, String  state) {

        //request form
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://referential.p.rapidapi.com/v1/city?iso_a2="+countryMap.get(country)+"&state_code="+stateMap.get(state)+"&limit=250"))
            .header("X-RapidAPI-Key", "80cfee487dmsh3c85dbf436bac52p19a664jsnab7a8bdfb0ad")
            .header("X-RapidAPI-Host", "referential.p.rapidapi.com")
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();

        //request from site and stores into response
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

        //parses the body into a json array
        JSONArray arr = new JSONArray(response.body());
        String[] cities = new String[arr.length()];

        //stores each city into the city array
        for(int i = 0; i < arr.length(); i++) {
            cities[i] = arr.getJSONObject(i).getString("value");
        }
        return cities;
    }
}
