//onload function to load the javascript only when the page has loaded completely
window.addEventListener("load", initialize);

//initialise funciton
function initialize() {
  //submit button and cancel button
  var submitButton = document.getElementById("submitbtn");
  var cancelButton = document.getElementById("cancelbtn");

  //functions to load after clicking login and cancel button
  if (submitButton) submitButton.addEventListener("click", redirect);
  if (cancelButton) cancelButton.addEventListener("click", stay);
}

//function to load the corresponding page when the user logins
function redirect() {
  window.location.replace("taskPage.html");
}

//function to stay on the same page when the user clicks the cancel button
function stay() {
  window.location.replace(
    "C:/Users/Shruti Agrawal/team-22/react/LoginPage/loginPage.html"
  );

  //clears the field of username and password after the cancel button is clicked
  document.getElementById("uname").innerHTML = "";
  document.getElementById("psw").innerHTML = "";
}
