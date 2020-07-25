window.addEventListener('load', date);

function date(){
	var today = new Date();
	var enddate = new Date();

var dd = today.getDate();
var edd = today.getDate()+1;

var mm = today.getMonth()+1; //January is 0!
var yyyy = today.getFullYear();
 if(dd<10){
        dd='0'+dd
    } 
    if(mm<10){
        mm='0'+mm
    } 

today = yyyy+'-'+mm+'-'+dd;
enddate= yyyy+'-'+mm+'-'+edd;
document.getElementById("startdate").setAttribute("min", today);
document.getElementById("enddate").setAttribute("min", enddate);
}