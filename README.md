# Rental Cars Test

This repository consists of a Java console application, which solves each set task in "Part 1". For "Part 2" the console application sends its data through a REST API via POST request and outputs the content to a HTML page. A GET request can have various parameters and is available for output in JSON or HTML. Database management is facilitated using MySQL.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine.

### Prerequisites

What things you need to run the software

```
Any Java IDE // (I use Eclipse)
XAMPP (If you want to run Web server and MySQL DB locally)

```

### Running Part 1

The Java files the task can be located with the following path

```
RentalCars/RentalCarsTask/src/defaultPackage/
```

On running the project a menu is presented with the following options.

```
[1] View Task 1
[2] View Task 2
[3] View Task 3
[4] View Task 4
[5] Send Vehicle Objects
```

For Example on entering the key...
```
3
```
...Task 3 will output.

### Running Part 2

The vehicle objects can be sent through the API using by entering.

```
5
```

Please note the Vehicle objects have already been sent through the REST API, as result by pressing "5" a duplicate set will be sent.

The output page is located at the following address.

```
http://www.endy.co.uk/outputpage.html
```

## API Parameters

Vehicle objects sent from the java application are stored a MySQL database.

The output page...

```
http://www.endy.co.uk/outputpage.html
```

... retreives the information using a series of requests, all of which have separate parameters to display specific data for each task.

For online GET requests use the following address

```
http://www.endy.co.uk/vehicles.php
```

Below shows the consequence of each parameter

|Parameter|Possible Values|Description| 
|--|--|--| 
|dataname|name,sipp,price,supplier,rating,car_type,doors,transmission,fuel,air_con,vehicle_score,sum_of_scores|Select the datatype/types to output. For multiple datatypes, separate each word with a "-"| 
|orderby|name,sipp,price,supplier,rating,car_type,doors,transmission,fuel,air_con,vehicle_score,sum_of_scores|Order by a certain type| 
|order|asc,dec|Present the information in ascending or descending order| 
|num|0 - inf|Specify the number of records to output| 
|format|json,html|Specify the output format| 

For example view the following GET request...

```
http://www.endy.co.uk/vehicles.php?dataname=name-price&orderby=price&order=asc&format=json
```

...would output the 'Name' and 'Price' of each vehicle, ordered by the price in ascending order in JSON format. 

## Demonstration

The video below displays the functionality of this project.

[![Rental Cars API Demo](https://www.youtube.com/watch?v=aS_5mNXQ0Xc/0.jpg)](https://www.youtube.com/watch?v=aS_5mNXQ0Xc "Rental Cars API Demo")

## Built With

* [Mars Eclipse](https://eclipse.org/mars/) - The IDE used.
* [Xammp](https://www.apachefriends.org/index.html) - Web testing
* [Paw](https://paw.cloud/) - API testing
* [JSON-simple](https://code.google.com/archive/p/json-simple/) - JSON Decoding

## Authors

* **O'Shea Douglas** - *Initial work* - [ENDY](https://github.com/endysis)

## Acknowledgments

* **Woodrow Barlow** - *This code was used to develop the aesthetic menu design of the output page* -[Woodrow Barlow](http://codepen.io/wbarlow/pen/NqLWXJ) 
* **Nikhil Krishnan** - *This code was used to develop the aesthetic table design of the output page* - [Nikhil Krishnan](http://codepen.io/nikhil8krishnan/pen/WvYPvv) 

