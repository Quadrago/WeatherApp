package com.mycompany.mavenproject1;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

class CSV {  

  public static void exportData(String[] location, String date, String[] weatherInfo, String[] clothingRec) {
    String path = "results.csv";

    // Create File
    try {
      File file = new File(path);
      if (file.createNewFile()) {
        System.out.println("File created: " + file.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    // Write File
    try {
      FileWriter writer = new FileWriter(path);

      // not sure if this part works
      writer.write("Country,State,City, Day Of Interest,Temperature,Humidity,Wind,Clothing Options\n");
      String text = String.join(",",location) + "," + String.join(",",weatherInfo) + ","+ String.join(",",clothingRec) + "\n";
      writer.write(text);
      
      writer.close();

      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }
  }
}