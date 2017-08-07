package defaultPackage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Task {
 public static void main(String[] args) {

  // REST API Link (Modify to local directory for localhost testing)
  String apiLink = "http://www.endy.co.uk/vehicles.php";

  // Read in JSON File
  ArrayList < Vehicle > vehicleArray = parseJSONToArray("vehicles.json");

  // Initialise Scanner
  Scanner scanner = new Scanner(System.in);

  // User Input
  String userInput = "";

  // Loop Exit Condition
  boolean b = true;

  do {

   System.out.println("__________________________________");
   System.out.println("Select options from following list");
   System.out.println("[1] View Part 1 - Task 1");
   System.out.println("[2] View Part 1 - Task 2");
   System.out.println("[3] View Part 1 - Task 3");
   System.out.println("[4] View Part 1 - Task 4");
   System.out.println("[5] Part 2 - POST Vehicle Objects");
   System.out.println("[0] Exit Program");
   System.out.println("__________________________________");

   userInput = scanner.nextLine();

   switch (userInput) {

    // Part 1 Task 1
    case "1":
     System.out.println("TASK 1");
     // Sort array using Comparator
     Collections.sort(vehicleArray, new Comparator < Vehicle > () {
      @Override public int compare(Vehicle v1, Vehicle v2) {
       return Double.compare(v1.returnPrice(), v2.returnPrice());
      }
     });
     // Output return the name and price of each vehicle object.
     for (int i = 0; i < vehicleArray.size(); i++) {
      System.out.println(vehicleArray.get(i).returnName() + " - " + vehicleArray.get(i).returnPrice());
     }
     break;

     // Part 1 Task 2
    case "2":
     System.out.println("TASK 2");

     for (int i = 0; i < vehicleArray.size(); i++) {
      System.out.println(vehicleArray.get(i).returnName() + " - " + vehicleArray.get(i).returnSipp() + " - " + vehicleArray.get(i).returnCarType() + " - " + vehicleArray.get(i).returnDoorsCarType() + " - " + vehicleArray.get(i).returnTransmission() + " - " + vehicleArray.get(i).returnFuel() + " - " + vehicleArray.get(i).returnAirCon());
     }
     break;

     // Part 1 Task 3	
    case "3":
     System.out.println("TASK 3");
     ArrayList<String> carTypeList = new ArrayList<String>();
     Collections.sort(vehicleArray, new Comparator < Vehicle > () { // Sort Array
      @Override public int compare(Vehicle v1, Vehicle v2) {
       return Double.compare(v2.returnRating(), v1.returnRating());
      }
     });
     
     for(int i = 0; i < vehicleArray.size(); i++){ 
    	 if(carTypeList.contains(vehicleArray.get(i).returnCarType()) == false){ // Check if the 'CarType is already checked' 
    		 carTypeList.add(vehicleArray.get(i).returnCarType()); // If not, add to the checked list and print the corresponding car type.
    		 System.out.println(vehicleArray.get(i).returnName() + " - " + vehicleArray.get(i).returnCarType() + " - " + vehicleArray.get(i).returnSupplier() + " - " + vehicleArray.get(i).returnRating());
    	 } else {
    		 continue; // Else continue and avoid printing the Car Type
    	 }
     }
     break;

     // Part 1 Task 4
    case "4":
     System.out.println("TASK 4");
     Collections.sort(vehicleArray, new Comparator < Vehicle > () {
      @Override public int compare(Vehicle v1, Vehicle v2) {
       return Double.compare(v2.returnSumOfScores(), v1.returnSumOfScores());
      }
     });

     for (int i = 0; i < vehicleArray.size(); i++) {
      System.out.println(vehicleArray.get(i).returnName() + " - " + vehicleArray.get(i).returnVehicleScore() + " - " + vehicleArray.get(i).returnRating() + " - " + vehicleArray.get(i).returnSumOfScores());
     }
     break;

     // Part 2 
    case "5":
     System.out.println("PART 2");
     for (int i = 0; i < vehicleArray.size(); i++) {
      String request = "vehicle_name=" + vehicleArray.get(i).returnName() +
       "&vehicle_sipp=" + vehicleArray.get(i).returnSipp() +
       "&vehicle_price=" + vehicleArray.get(i).returnPrice() +
       "&vehicle_supplier=" + vehicleArray.get(i).returnSupplier() +
       "&vehicle_rating=" + vehicleArray.get(i).returnRating() +
       "&vehicle_type=" + vehicleArray.get(i).returnCarType() +
       "&vehicle_doors=" + vehicleArray.get(i).returnDoorsCarType() +
       "&vehicle_trans=" + vehicleArray.get(i).returnTransmission() +
       "&vehicle_fuel=" + vehicleArray.get(i).returnFuel() +
       "&vehicle_aircon=" + vehicleArray.get(i).returnAirCon() +
       "&vehicle_score=" + vehicleArray.get(i).returnVehicleScore() +
       "&vehicle_sum=" + vehicleArray.get(i).returnSumOfScores();
      sendToRESTAPI(request, apiLink);
     }
     System.out.println("Request Sent");
     break;

    case "0":
     System.out.println("Exiting Program");
     System.exit(0);
     b = false;
     break;

    default:
     System.out.print("Wrong Input Entered - Please try again");
     break;
   }
   System.out.println("\n");
  } while (b == true);
 }

 private static ArrayList < Vehicle > parseJSONToArray(String path) {
  ArrayList < Vehicle > output = new ArrayList < Vehicle > ();
  JSONParser parser = new JSONParser();
  try {
   Object object = parser.parse(new FileReader(path));

   JSONObject jsonObject = (JSONObject) object;
   JSONObject search = (JSONObject) jsonObject.get("Search");
   JSONArray vehicleList = (JSONArray) search.get("VehicleList");
   JSONObject vehicle;
   Vehicle v;

   for (int i = 0; i < vehicleList.size(); i++) {

    vehicle = (JSONObject) vehicleList.get(i);
    v = new Vehicle((String) vehicle.get("sipp"), (String) vehicle.get("name"), (double) vehicle.get("price"), (String) vehicle.get("supplier"), Double.parseDouble(vehicle.get("rating").toString()));
    output.add(v);

   }
  } catch (FileNotFoundException e) {
   e.printStackTrace();
  } catch (IOException e) {
   e.printStackTrace();
  } catch (Exception e) {
   e.printStackTrace();
  }

  return output;
 }

 private static void sendToRESTAPI(String s, String link) {
  try {
   String urlParameters = s;
   URL url = new URL(link);
   URLConnection conn = url.openConnection();

   conn.setDoOutput(true);

   OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

   writer.write(urlParameters);
   writer.flush();

   String line;
   BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

   while ((line = reader.readLine()) != null) {
    System.out.println(line);
   }
   writer.close();
   reader.close();
  } catch (MalformedURLException e) {
   e.printStackTrace();
  } catch (IOException e) {
   e.printStackTrace();
  }
 }
}