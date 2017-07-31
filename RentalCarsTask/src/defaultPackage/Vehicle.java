package defaultPackage;

public class Vehicle {
	String sipp;
	String name;
	double price;
	String supplier;
	double rating;
	
	public Vehicle(String s, String n, double p, String su, double r){
		sipp = s;
		name = n;
		price = p;
		supplier = su;
		rating = r;
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
}
