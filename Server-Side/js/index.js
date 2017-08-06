$(document).ready(function() {
    //Load each table
    $.ajax({
        url: 'http://localhost/rentalOutput/php/vehicles.php?dataname=name-price&order=asc&orderby=price&format=html',
        error: function(e) {
            console.log('error', e)
        },
        success: function(data) {
            $('.tbl-containerT0').html(data);
        },
        type: 'GET'
    });

    $.ajax({
        url: 'http://localhost/rentalOutput/php/vehicles.php?dataname=name-sipp-car_type-doors-transmission-fuel-air_con&order=asc&orderby=name&format=html',
        error: function(e) {
            console.log('error', e)
        },
        success: function(data) {
            $('.tbl-containerT1').html(data);
        },
        type: 'GET'
    });

    $.ajax({
        url: 'http://localhost/rentalOutput/php/vehicles.php?dataname=name-car_type-supplier-rating&order=dec&orderby=rating&format=html',
        error: function(e) {
            console.log('error', e)
        },
        success: function(data) {
            $('.tbl-containerT2').html(data);
        },
        type: 'GET'
    });

    $.ajax({
        url: 'http://localhost/rentalOutput/php/vehicles.php?dataname=name-vehicle_score-rating-sum_of_scores&order=dec&orderby=sum_of_scores&format=html',
        error: function(e) {
            console.log('error', e)
        },
        success: function(data) {
            $('.tbl-containerT3').html(data);
        },
        type: 'GET'
    });
    // Hide all on launching
    $('.tbl-containerT0').hide();
    $('.tbl-containerT1').hide();
    $('.tbl-containerT2').hide();
    $('.tbl-containerT3').hide();

});


$('.panel').click(function() {
    if (!$(this).hasClass('active')) {
        var tblContainer = ".tbl-containerT";
        var index = $(this).index();
        console.log(index);
        tblContainer = tblContainer.concat(index);
        $('#order').removeClass();
        $('#order').addClass('opt' + (index + 1));
        $('#choice').get(0).selectedIndex = index;
        $(this).siblings().addClass('hidden');
        $(this).addClass('active');
        $('#order').delay(800).slideToggle(400);
        $('.name').hide();
        console.log(tblContainer);
        $(tblContainer).show();
    }
});

$('#back').click(function(e) {
    $('.name').show();
    $('.tbl-containerT0').hide();
    $('.tbl-containerT1').hide();
    $('.tbl-containerT2').hide();
    $('.tbl-containerT3').hide();
    $('#order').slideToggle(400);
    var self = this;
    setTimeout(function() {
        $('.panel').removeClass('hidden active');
    }, 400);
    e.preventDefault();
});