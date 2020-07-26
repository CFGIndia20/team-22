window.addEventListener("load", date);

function date() {
  var today = new Date();
  var enddate = new Date();

  var dd = today.getDate();
  var edd = today.getDate() + 1;

  var mm = today.getMonth() + 1; //January is 0!
  var yyyy = today.getFullYear();
  if (dd < 10) {
    dd = "0" + dd;
  }
  if (mm < 10) {
    mm = "0" + mm;
  }

  today = yyyy + "-" + mm + "-" + dd;
  enddate = yyyy + "-" + mm + "-" + edd;
  document.getElementById("startdate").setAttribute("min", today);
  document.getElementById("enddate").setAttribute("min", enddate);
}

function rowCreate(){
  var table = document.getElementById("eventTable");

// Create an empty <tr> element and add it to the 1st position of the table:
var row = table.insertRow(0);

// Insert new cells (<td> elements) at the 1st and 2nd position of the "new" <tr> element:
var cell1 = row.insertCell(0);
var cell2 = row.insertCell(1);
var cell3 = row.insertCell(2);
var cell4 = row.insertCell(3);
var cell5 = row.insertCell(4);

// Add some text to the new cells:
cell1.innerHTML = "NEW CELL1";
cell2.innerHTML = "NEW CELL2";
cell3.innerHTML = "NEW CELL1";
cell4.innerHTML = "NEW CELL2";
cell5.innerHTML = "NEW CELL1";
}