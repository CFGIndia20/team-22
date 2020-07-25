window.addEventListener("load", initialize);

function initialize() {
  var submitButton = document.getElementById("submitbtn");
  var cancelButton = document.getElementById("cancelbtn");
  if (submitButton) submitButton.addEventListener("click", redirect);
  if (cancelButton) cancelButton.addEventListener("click", stay);
}

function redirect() {
  window.location.replace(
    "C:/Users/Shruti Agrawal/team-22/react/redirect.html"
  );
}

function stay() {
  window.location.replace(
    "C:/Users/Shruti Agrawal/team-22/react/loginPage.html"
  );
  document.getElementById("uname").innerHTML = "";
  document.getElementById("psw").innerHTML = "";
}
