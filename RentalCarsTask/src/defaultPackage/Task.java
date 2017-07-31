package defaultPackage;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Task {
	public static void main(String[] args) {
		ArrayList<Vehicle> vehicleArray = new ArrayList<Vehicle>();
		JSONObject j;
		
		JSONParser parser = new JSONParser(); 
		try{
			Object object = parser.parse(new FileReader("vehicles.json"));
			
			JSONObject jsonObject = (JSONObject) object;
			JSONObject search = (JSONObject) jsonObject.get("Search");
			JSONArray vehicleList = (JSONArray) search.get("VehicleList");
			JSONObject vehicle;
			Vehicle v;
			
			for(int i = 0; i < vehicleList.size(); i++){
				vehicle = (JSONObject) vehicleList.get(i);
				v = new Vehicle((String) vehicle.get("sipp"),(String) vehicle.get("name"),(double) vehicle.get("price"),(String) vehicle.get("supplier"),Double.parseDouble(vehicle.get("rating").toString()));
				System.out.println(vehicle.get("rating").getClass());
	
				vehicleArray.add(v);
			}
		} 
		catch(FileNotFoundException e) {e.printStackTrace();}
		catch(IOException e) {e.printStackTrace();}
		catch(Exception e) {e.printStackTrace();}
	
		System.out.println(vehicleArray.size());
		
		
		for(int i = 0; i < vehicleArray.size(); i++){
		System.out.println(vehicleArray.get(i).returnRating());
		}
	
	}
}