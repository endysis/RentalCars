<?php


// Databast connection information
$host = "localhost";
$user = "root";
$pwd = "ripley";
$db = "RentalCars";

//Connect to the database
try {
	echo "Connected";
	$conn = new PDO("mysql:host=$host;dbname=$db", $user , $pwd);
	$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (Exception $e){
	die(var_dump($e));
}

if($_SERVER['REQUEST_METHOD'] == "GET"){
	echo "Hello World";
} else if($_SERVER['REQUEST_METHOD'] == "POST" && !empty($_POST)) {
	try{
		$vehicle_name =	 $_POST['vehicle_name'];
		$vehicle_sipp = $_POST['vehicle_sipp'];
		$vehicle_price = $_POST['vehicle_price'];
		$vehicle_supplier = $_POST['vehicle_supplier'];
		$vehicle_rating = $_POST['vehicle_rating'];
		$vehicle_type = $_POST['vehicle_type'];
		$vehicle_doors= $_POST['vehicle_doors'];
		$vehicle_trans = $_POST['vehicle_trans'];
		$vehicle_fuel = $_POST['vehicle_fuel'];
		$vehicle_aircon = $_POST['vehicle_aircon'];
		$vehicle_score = $_POST['vehicle_score'];
		$vehicle_sum = $_POST['vehicle_sum'];

		$sql_insert = "INSERT INTO `Vehicles` (`NAME`, `SIPP`,`PRICE`,`SUPPLIER`,`RATING`,`CAR_TYPE`,`DOORS`,`TRANSMISSION`,`FUEL`,`AIR_CON`,`VEHICLE_SCORE`,`SUM_OF_SCORES`)
		 				VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

		$stmt = $conn->prepare($sql_insert);
		$stmt->bindValue(1,$vehicle_name);
		$stmt->bindValue(2,$vehicle_sipp);
		$stmt->bindValue(3,$vehicle_price);
		$stmt->bindValue(4,$vehicle_supplier);
		$stmt->bindValue(5,$vehicle_rating);
		$stmt->bindValue(6,$vehicle_type);
		$stmt->bindValue(7,$vehicle_doors);
		$stmt->bindValue(8,$vehicle_trans);
		$stmt->bindValue(9,$vehicle_fuel);
		$stmt->bindValue(10,$vehicle_aircon);
		$stmt->bindValue(11,$vehicle_score);
		$stmt->bindValue(12,$vehicle_sum);
		$stmt->execute();

} catch(Exception $e) {
	die(var_dump($e)); 
}
echo "<h3> Data Sent </h3>";

	} else  {
	http_response_code(405);
} 
?>