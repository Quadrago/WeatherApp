package com.mycompany.mavenproject1;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

class CSV {  

  public static void exportData(String[] location, String date, String[] weatherInfo, String[] clothingRec) {
    System.out.println("result");
    String path = "results.csv";

    FileWriter writer = null;
    File f = new File(path);
    if(!f.exists() && !f.isDirectory()) { 
        try {
          writer = new FileWriter(path, true);
          writer.write("Country,State,City,Day Of Interest,Temperature,Humidity,Wind,Clothing Options\n");
          writer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
    } 
    else {
      System.out.println("File already exists.");
    }

    // Write File
    try {
      writer = new FileWriter(path, true);
      String text = String.join(",",location) + "," + date + "," + String.join(",",weatherInfo) + ","+ String.join(",",clothingRec) + "\n";
      writer.write(text);
      
      writer.close();

      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }
  }
}