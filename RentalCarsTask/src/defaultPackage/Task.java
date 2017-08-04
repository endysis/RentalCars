package defaultPackage;
import java.io.BufferedReader;
import java.io.Console;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Task {
	public static void main(String[] args) {
		
		String paramKey = "vehicle=";
		String value = "";
		String apiLink = "http://localhost/restAPI.php";
		String apiLink2 = "http://localhost/rentalOutput/php/restAPI.php";
		
		// Read in JSON File
		ArrayList<Vehicle> vehicleArray = parseJSONToArray("vehicles.json");
		
		// Map Arrays
		Map<String,String> carType = new HashMap<String,String>();
		
		// Task 1
		Collections.sort(vehicleArray, new Comparator<Vehicle>(){
			@Override public int compare(Vehicle v1, Vehicle v2){
				return Double.compare(v1.returnPrice(), v2.returnPrice());
			}
		});
		
		for(int i = 0; i < vehicleArray.size(); i++){
			value = vehicleArray.get(i).returnName() + " - " + vehicleArray.get(i).returnPrice();
			System.out.println(value);
			//sendToRESTAPI(paramKey+value,apiLink);
			}
		
		// Task 2
		for(int i = 0; i < vehicleArray.size(); i++){
			System.out.println(vehicleArray.get(i).returnName() + " - " + vehicleArray.get(i).returnSipp() + " - " + vehicleArray.get(i).returnCarType() + " - " + vehicleArray.get(i).returnDoorsCarType() + " - " + vehicleArray.get(i).returnTransmission() + " - " + vehicleArray.get(i).returnFuel() + " - " + vehicleArray.get(i).returnAirCon());
		}	
		
		// Task 3
		Collections.sort(vehicleArray, new Comparator<Vehicle>(){
			@Override public int compare(Vehicle v1, Vehicle v2){
				return Double.compare(v2.returnRating(), v1.returnRating());
			}
		});
		
		for(int i = 0; i < vehicleArray.size(); i++){
			System.out.println(vehicleArray.get(i).returnName() + " - " + vehicleArray.get(i).returnPrice() + " - " + vehicleArray.get(i).returnSupplier() + " - " + vehicleArray.get(i).returnRating());
			}
	
		System.out.println("-------------");	
		
		// Task 4
		Collections.sort(vehicleArray, new Comparator<Vehicle>(){
			@Override public int compare(Vehicle v1, Vehicle v2){
				return Double.compare(v2.returnSumOfScores(), v1.returnSumOfScores());
			}
		});
		
		for(int i = 0; i < vehicleArray.size(); i++){
			System.out.println(vehicleArray.get(i).returnName() + " - " + vehicleArray.get(i).returnVehicleScore() + " - " + vehicleArray.get(i).returnRating() + " - " + vehicleArray.get(i).returnSumOfScores());
			}
	
		/*
		for(int i = 0; i < vehicleArray.size(); i++){
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

		sendToRESTAPI(request,apiLink2);
		}
		*/
		
		System.out.println(vehicleArray.size());
		
		
		String request = "vehicle_name=" + vehicleArray.get(0).returnName() +
				"&vehicle_sipp=" + vehicleArray.get(0).returnSipp() +
				"&vehicle_price=" + vehicleArray.get(0).returnPrice() +
				"&vehicle_supplier=" + vehicleArray.get(0).returnSupplier() +
				"&vehicle_rating=" + vehicleArray.get(0).returnRating() +
				"&vehicle_type=" + vehicleArray.get(0).returnCarType() +
				"&vehicle_doors=" + vehicleArray.get(0).returnDoorsCarType() +
				"&vehicle_trans=" + vehicleArray.get(0).returnTransmission() +
				"&vehicle_fuel=" + vehicleArray.get(0).returnFuel() +
				"&vehicle_aircon=" + vehicleArray.get(0).returnAirCon() +
				"&vehicle_score=" + vehicleArray.get(0).returnVehicleScore() +
				"&vehicle_sum=" + vehicleArray.get(0).returnSumOfScores();
		
		sendToRESTAPI(request,apiLink2);
		
		
	
	}
	
	private static void sendToRESTAPI(String s, String link){
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
		}
		catch (MalformedURLException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	
	private static ArrayList<Vehicle> parseJSONToArray(String path){
		ArrayList<Vehicle> output = new ArrayList<Vehicle>();
		JSONParser parser = new JSONParser(); 
		try{
			Object object = parser.parse(new FileReader(path));
			
			JSONObject jsonObject = (JSONObject) object;
			JSONObject search = (JSONObject) jsonObject.get("Search");
			JSONArray vehicleList = (JSONArray) search.get("VehicleList");
			JSONObject vehicle;
			Vehicle v;
			
			for(int i = 0; i < vehicleList.size(); i++){
				
				vehicle = (JSONObject) vehicleList.get(i);
				v = new Vehicle((String) vehicle.get("sipp"),(String) vehicle.get("name"),(double) vehicle.get("price"),(String) vehicle.get("supplier"),Double.parseDouble(vehicle.get("rating").toString()));
				output.add(v);
				
			}
		} 
		catch(FileNotFoundException e) {e.printStackTrace();}
		catch(IOException e) {e.printStackTrace();}
		catch(Exception e) {e.printStackTrace();}
		
		return output;
	}
	
	
	
	
	
	
	
	
	
	
	/*
	 * 
	 * 
	 * int[] arr = new int[10];
	Random rand = new Random();
	
	for(int i = 0; i < arr.length; i++){
		arr[i] = rand.nextInt(10) + 1;
	}
	 */
	
	
	
	
	
	
	
	/*private int[] sort(int arr[], int lo, int hi){
		
		if(hi - lo <= 0){
			return arr;
		} else {
			int splitPoint = split(arr, lo, hi);
			sort(arr,lo,splitPoint - 1);
			sort(arr,splitPoint + 1,hi);
		}
		
		
		return arr;
	}
	
	
	
	
	private int[] sort(int [] arr, int arrLength){
		
		if(arrLength <= 1){
			return arr;
		} else {
			sort(arr,0,arrLength - 1);
		}
		return arr;
	}*/
	
	
}






































