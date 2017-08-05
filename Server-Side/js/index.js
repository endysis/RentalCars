$(document).ready(function () {
  $.ajax({
   url: 'http://localhost/rentalOutput/php/getVehicles.php?num=2&dataname=name-price-sum_of_scores',

   error: function(e) {
      console.log('error', e)
   },
   success: function(data) {
      $('.tbl-container').html(data);
   },
   type: 'GET'
   }); 
    $('.tbl-header').hide();
    $('.tbl-content').hide();
});   


$('.panel').click(function() {
  if(!$(this).hasClass('active')) {
    var index = $(this).index();
    $('#order').removeClass();
    $('#order').addClass('opt'+(index+1));
    $('#choice').get(0).selectedIndex = index;
    $(this).siblings().addClass('hidden');
    $(this).addClass('active');
    $('#order').delay(800).slideToggle(400);
    $('.name').hide();
    $('.tbl-header').show();
    $('.tbl-content').show();
  }
});

$('#back').click(function(e) {
  $('.name').show();
    $('.tbl-header').hide();
    $('.tbl-content').hide();
  $('#order').slideToggle(400);
  var self = this;
  setTimeout(function() {
    $('.panel').removeClass('hidden active');
  }, 400);
  e.preventDefault();
});

