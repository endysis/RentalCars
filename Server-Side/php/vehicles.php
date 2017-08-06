 <?php

// Databast connection information
$host = "localhost";
$user = "root";
$pwd  = "ripley";
$db   = "RentalCars";

//Connect to the database
try {
    $conn = new PDO("mysql:host=$host;dbname=$db", $user, $pwd);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
}
catch (Exception $e) {
    die(var_dump($e));
}

if ($_SERVER['REQUEST_METHOD'] == "GET") {
    // Queries
    $format    = isset($_GET['format']) && strtolower($_GET['format']) == 'html' ? "html" : "json";
    $data_name = isset($_GET['dataname']);
    if ($data_name) {
        $data_name = str_replace("-", ",", strtoupper($_GET['dataname']));
    } else {
        $data_name = "*";
    }
    $number_of_vehicles = isset($_GET['num']) ? intval($_GET['num']) : 1000;
    $order              = isset($_GET['order']) && strtolower($_GET['order']) == 'dec' ? 'DESC' : 'ASC';
    $order_by           = isset($_GET['orderby']) ? strtoupper($_GET['orderby']) : 'NAME';
    
    
    $counter    = 1;
    $sql_select = "SELECT $data_name FROM `Vehicles` ORDER BY $order_by $order LIMIT $number_of_vehicles"; //  $sql_select = "SELECT * FROM mtbl ".$sql;
    $stmt       = $conn->query($sql_select);
    $vehicles   = $stmt->fetchAll();
    
    
    if ($format == 'json') {
        $stmt->execute();
        $array = $stmt->fetchAll(PDO::FETCH_ASSOC);
        header('Content-type: application/json');
        echo json_encode(array('Vehicles' => $array));
    }
    
    else {
        $allColumns = "NAME,SIPP,PRICE,SUPPLIER,RATING,CAR_TYPE,DOORS,TRANSMISSION,FUEL,AIR_CON,VEHICLE_SCORE,SUM_OF_SCORES";
        if ($data_name == "*") {
            $data_name = $allColumns;
        }
        // Extract data requests from 'data_name' query string and place in an array
        $tableHeaders = array();
        $data_name    = $data_name . ",";
        $stringLength = strlen($data_name);
        for ($x = 0; $x < $stringLength; $x++) {
            if ($data_name[$x] == ",") {
                array_push($tableHeaders, substr($data_name, 0, $x));
                $data_name    = substr($data_name, ($x + 1), $stringLength);
                $stringLength = strlen($data_name);
                $x            = 0;
            }
            if ($stringLength == 0) {
                break;
            }
        }
        
        if (count($vehicles) > 0) {
?>
<html>
 <head>
 </head>
 <body>
   <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0">
      <thead>
      <tr>
        <?php
            for ($i = 0; $i < count($tableHeaders); $i++) {
?><th><?php
                echo $tableHeaders[$i];
?></th><?php
            }
?>
  </tr>
      </thead>
    </table>
  </div>

  <div class="tbl-content">
    <table cellpadding="0" cellspacing="0" border="0">
      <tbody class = "tbl-body">
        <?php
            foreach ($vehicles as $vehicle) {
?>
          <tr> 
          <?php
                for ($i = 0; $i < count($tableHeaders); $i++) {
?><td><?php
                    echo $vehicle[$tableHeaders[$i]];
?></td>
          <?php
                }
?>
          </tr>
            <?php
            }
?>
      </tr>
      </tbody>
    </table>
  </div>    
   </body>
 </html>      
    <?php
            $counter++;
        } else {
            echo "<h3>No car is currently registered.</h3>";
        }
    }
} else if ($_SERVER['REQUEST_METHOD'] == "POST" && !empty($_POST)) {
    try {
        $vehicle_name     = $_POST['vehicle_name'];
        $vehicle_sipp     = $_POST['vehicle_sipp'];
        $vehicle_price    = $_POST['vehicle_price'];
        $vehicle_supplier = $_POST['vehicle_supplier'];
        $vehicle_rating   = $_POST['vehicle_rating'];
        $vehicle_type     = $_POST['vehicle_type'];
        $vehicle_doors    = $_POST['vehicle_doors'];
        $vehicle_trans    = $_POST['vehicle_trans'];
        $vehicle_fuel     = $_POST['vehicle_fuel'];
        $vehicle_aircon   = $_POST['vehicle_aircon'];
        $vehicle_score    = $_POST['vehicle_score'];
        $vehicle_sum      = $_POST['vehicle_sum'];
        
        $sql_insert = "INSERT INTO `Vehicles` (`NAME`, `SIPP`,`PRICE`,`SUPPLIER`,`RATING`,`CAR_TYPE`,`DOORS`,`TRANSMISSION`,`FUEL`,`AIR_CON`,`VEHICLE_SCORE`,`SUM_OF_SCORES`)
                         VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        
        $stmt = $conn->prepare($sql_insert);
        $stmt->bindValue(1, $vehicle_name);
        $stmt->bindValue(2, $vehicle_sipp);
        $stmt->bindValue(3, $vehicle_price);
        $stmt->bindValue(4, $vehicle_supplier);
        $stmt->bindValue(5, $vehicle_rating);
        $stmt->bindValue(6, $vehicle_type);
        $stmt->bindValue(7, $vehicle_doors);
        $stmt->bindValue(8, $vehicle_trans);
        $stmt->bindValue(9, $vehicle_fuel);
        $stmt->bindValue(10, $vehicle_aircon);
        $stmt->bindValue(11, $vehicle_score);
        $stmt->bindValue(12, $vehicle_sum);
        $stmt->execute();
        
    }
    catch (Exception $e) {
        die(var_dump($e));
    }
    echo "<h3> Data Sent </h3>";
    
} else {
    http_response_code(405);
}
?> 