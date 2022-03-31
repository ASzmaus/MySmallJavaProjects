package zad1;


import java.io.*;
import java.util.*;

//The application adds the content of the offer files to the database, it is internationalized.
//It presents the customer in the table with the full set of offers in the language chosen by him and according to the selected regional settings.

public class Main {

  public static void main(String[] args) {
    File dataDir = new File("data");
    TravelData travelData = new TravelData(dataDir);
    String dateFormat = "yyyy-MM-dd";
    for (String locale : Arrays.asList("pl_PL", "en_GB")) {
      List<String> odlist = travelData.getOffersDescriptionsList(locale, dateFormat);
      for (String od : odlist) System.out.println(od);
    }
    // --- database part
    String url =  "jdbc:sqlite:zad1.db";
    Database db = new Database(url, travelData);
    db.create();
    db.showGui();
  }

}
