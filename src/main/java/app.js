$(document).ready(function() {
    function myFunction() {
        var x = document.getElementById("mySelect").value;
        document.getElementById("demo").innerHTML = "You selected: " + x;}

        //Stops the submit request
    $("#myAjaxRequestForm").submit(function (e) {
        e.preventDefault();
    });

});