package defaultPackage;

import java.util.Map;

public class Vehicle {
	String sipp;
	String name;
	double price;
	String supplier;
	double rating;
	double vehicleScore;
	double sumOfScores;
	
	public Vehicle(String s, String n, double p, String su, double r){
		sipp = s;
		name = n;
		price = p;
		supplier = su;
		rating = r;
		this.setVehicleScore();
	}
	
	
	public String returnSipp(){
		return sipp;
	}
	
	public String returnName(){
		return name;
	}
	
	public double returnPrice(){
		return price;
	}
	
	public String returnSupplier(){
		return supplier;
	}
	
	public double returnRating(){
		return rating;
	}
	
	
	public String returnCarType(){
		String value = "";
		
		if(sipp.charAt(0) == 'M'){
			value = "Mini";
		} else if (sipp.charAt(0) == 'E') {
			value = "Economy";
		} else if (sipp.charAt(0) == 'C') {
			value = "Compcact";
		} else if (sipp.charAt(0) == 'I') {
			value = "Intermediate";
		} else if (sipp.charAt(0) == 'S') {
			value = "Standard";
		} else if (sipp.charAt(0) == 'F') {
			value = "Full Size";
		} else if (sipp.charAt(0) == 'P') {
			value = "Premium";
		} else if (sipp.charAt(0) == 'L') {
			value = "Luxury";
		} else if (sipp.charAt(0) == 'X') {
			value = "Special";
		}
		return value;
	}
	
	public String returnDoorsCarType(){
		String value = "N/A";
		
		if(sipp.charAt(1) == 'B'){
			value = "2 doors";
		} else if (sipp.charAt(1) == 'C') {
			value = "4 doors";
		} else if (sipp.charAt(1) == 'D') {
			value = "5 doors";
		} else if (sipp.charAt(1) == 'W') {
			value = "Estate";
		} else if (sipp.charAt(1) == 'T') {
			value = "Convertable";
		} else if (sipp.charAt(1) == 'F') {
			value = "SUV";
		} else if (sipp.charAt(1) == 'P') {
			value = "Pick Up";
		} else if (sipp.charAt(1) == 'V') {
			value = "Passenger Van";
		}
		return value;
	}
	
	public String returnTransmission(){
		String value = "";
		
		if(sipp.charAt(2) == 'M'){
			value = "Manual";
		} else {
			value = "Automatic";
		}
		
		return value;
	}
	
	public String returnFuel(){ // This is here only for for future expansion
		String value = "";
		
		if(sipp.charAt(3) == 'N'){
			value = "Petrol";
		} else {
			value = "Petrol";
		}
		return value; 
	}
	
	public String returnAirCon(){
		String value = "";
		
		if(sipp.charAt(3) == 'N'){
			value = "No Air Conditioning";
		} else {
			value = "Air Conditioning";
		}
		return value; 
	}
	
	public void setVehicleScore(){
		double score = 0;
		
		if(this.returnTransmission() == "Manual"){
			score++;
		} else{
			score += 5;
		}
		
		if(this.returnAirCon() == "Air Conditioning"){
			score += 2;
		}
		vehicleScore = score;
		sumOfScores = score + rating;	
	}
	
	public double returnVehicleScore(){
		return vehicleScore;
	}
	
	public double returnSumOfScores(){
		return sumOfScores;
	}
	
	
	
	
	
	
	
	
	
	public String returnSIPPSpec(){
		String value = "";
		String output = name + " - " + sipp + " - ";
		
		if(sipp.charAt(0) == 'M'){
			value = "Mini";
		} else if (sipp.charAt(0) == 'E') {
			value = "Economy";
		} else if (sipp.charAt(0) == 'C') {
			value = "Compcact";
		} else if (sipp.charAt(0) == 'I') {
			value = "Intermediate";
		} else if (sipp.charAt(0) == 'S') {
			value = "Standard";
		} else if (sipp.charAt(0) == 'F') {
			value = "Full Size";
		} else if (sipp.charAt(0) == 'P') {
			value = "Premium";
		} else if (sipp.charAt(0) == 'L') {
			value = "Luxury";
		} else if (sipp.charAt(0) == 'X') {
			value = "Special";
		}
		
		output = output + value + " - ";
		
		if(sipp.charAt(1) == 'B'){
			value = "2 doors";
		} else if (sipp.charAt(1) == 'C') {
			value = "4 doors";
		} else if (sipp.charAt(1) == 'D') {
			value = "5 doors";
		} else if (sipp.charAt(1) == 'W') {
			value = "Estate";
		} else if (sipp.charAt(1) == 'T') {
			value = "Convertable";
		} else if (sipp.charAt(1) == 'F') {
			value = "SUV";
		} else if (sipp.charAt(1) == 'P') {
			value = "Pick Up";
		} else if (sipp.charAt(1) == 'V') {
			value = "Passenger Van";
		}
		
		output = output + value + " - ";
		
		if(sipp.charAt(2) == 'M'){
			value = "Manual";
		} else {
			value = "Automatic";
		}
		
		output = output + value + " - ";
		
		if(sipp.charAt(3) == 'N'){
			value = "Petrol - No Air Conditioning";
		} else {
			value = "Petrol - Air Conditioning";
		}
		
		output = output + value;	
		
		return output;
	}
}












































