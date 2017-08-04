$(document).ready(function () {
$.ajax({
   url: 'http://localhost/rentalOutput/php/getVehicles.php', //or .. AZgetartists.php

   error: function(e) {
      console.log('error', e)
   },
   success: function(data) {
      $('.tbl-body').html(data);
   },
   type: 'GET'
}); 
});	  




/*

$(document).ready(function () {
$.ajax({
   url: 'http://localhost/getartists.php',
   data: { long: '0.54' // If passing in data
   },
   error: function(e) {
      console.log('error', e)
   },
   success: function(data) {
      $('.swiperwrapper').html(data);
   },
   type: 'GET'
});
		
});	  
*/