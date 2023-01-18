package com.mycompany.mavenproject1;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

class CSV {  

  public static void exportData(String name, String[] location, String date, String[] weatherInfo, String[] clothingRec) {
    String path = "results.csv";

    FileWriter writer = null;
    File f = new File(path);
    if(!f.exists() && !f.isDirectory()) { 
        try {
          writer = new FileWriter(path, true);
          writer.write("Name,Country,State,City,Day Of Interest,Temperature,Humidity,Wind,Clothing Options\n");
          writer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
    } 

    // Write File
    try {
      writer = new FileWriter(path, true);
      String text = name  + "," + String.join(",",location) + "," + date.replace(" ", ",") + "," + String.join(",",weatherInfo) + ","+ String.join(",",clothingRec) + "\n";
      writer.write(text);
      
      writer.close();
    } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }
  }
}