<html>
 <head>
 </head>
 <body>

 <?php
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

// Queries
$data_name = isset($_GET['dataname']);
if($data_name){
$data_name = str_replace("-", ",", strtoupper($_GET['dataname']));
} else {
    $data_name = "*";
}


$number_of_vehicles = isset($_GET['num']) ? intval($_GET['num']) : 1000;
$order = isset($_GET['order']) && strtolower($_GET['order']) == 'dec' ? 'DESC' : 'ASC';
$order_by = isset($_GET['orderby']) ? strtoupper($_GET['orderby']) : 'NAME';

 $counter = 1;
 $sql_select = "SELECT $data_name FROM `Vehicles` ORDER BY $order_by $order LIMIT $number_of_vehicles";   //  $sql_select = "SELECT * FROM mtbl ".$sql;
 $stmt = $conn->query($sql_select);
 $vehicles = $stmt->fetchAll(); 
 if(count($vehicles) > 0) {
?>
    <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0">
      <thead>
      <tr>
        <?php
        $y = 0;
        $stringLength = strlen($data_name);
        for($x = 0; $x < $stringLength; $x++){
            if($data_name[$x] == ","){
              ?><th><?php echo substr($data_name,0,($x));?></th><?php  
                $data_name = substr($data_name, ($x+1),$stringLength);
                $stringLength = strlen($data_name);
                $x = 0;
            }
            if($stringLength == 0){
                break;
            }
        }
    ?>
    </tr>
      </thead>
    </table>
  </div>

  <div class="tbl-content">
    <table cellpadding="0" cellspacing="0" border="0">
      <tbody class = "tbl-body">
      <div class = "row">
        <tr> 
        <?php
          foreach($vehicles as $vehicle) { ?>
          <td><?php echo $vehicle['NAME'];?></td>
          <td><?php echo $vehicle['PRICE']?></td> 
       <?php } ?>
        </tr>
        </div>
      </tbody>
    </table>
  </div>	
        
    <?php 
	$counter++;
	}
     else {
     echo "<h3>No car is currently registered.</h3>";
 }
 ?>
 </body>
 </html>
 
 
 
 
 
 
 
 


















 
 