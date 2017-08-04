<html>
 <head>

 </head>
 <body>
 <?php

 /*
 $long = $_GET["long"];
 if(isset( $long)){
	 $sql = 'WHERE `longitude` = "0.59"';
 
 }*/ 

 /*
 $long = $_GET["long"];
 if(isset( $long)){
	 $sql = 'WHERE `longitude` BETWEEN -0.5 AND -0.6
			AND `latitude` BETWEEN 50 AND 60
	 ';
 
 }*/ 


	 // DB connection info
$host = "localhost";
$user = "root";
$pwd = "ripley";
$db = "RentalCars";
 // Connect to database.
 try {
	 //echo "Connected";
     $conn = new PDO( "mysql:host=$host;dbname=$db", $user, $pwd);
     $conn->setAttribute( PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION );
 }
 catch(Exception $e){
     die(var_dump($e));
 }
 
 
 $counter = 1;
 
 
 $sql_select = "SELECT * FROM `Vehicles`";   //  $sql_select = "SELECT * FROM mtbl ".$sql;
 $stmt = $conn->query($sql_select);
 $vehicles = $stmt->fetchAll(); 
 if(count($vehicles) > 0) {
     foreach($vehicles as $vehicle) {
		 ?>
		<div class = "row">
        <tr>
          <td><?php echo $vehicle['NAME'];?></td>
          <td><?php echo $vehicle['PRICE']?></td>
        </tr>
        </div>
        
    <?php 
	$counter++;
	}
 } else {
     echo "<h3>No car is currently registered.</h3>";
 }
 ?>
 </body>
 </html>
 
 
 
 
 
 
 
 
 
 