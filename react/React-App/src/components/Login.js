import React, { Component } from 'react';

export default class Login extends Component {

  render() {
    return (
        <div>
        <form action="action_page.php" method="post">
          <div class="container">
 
            <label for="uname"><b>Username</b></label
            ><br />
            <input
              type="text"
              placeholder="Enter Username"
              name="uname"
              id="uname"
              required
            /><br />
 
            <label for="psw"><b>Password</b></label
            ><br />
            <input
              type="password"
              placeholder="Enter Password"
              name="psw"
              id="psw"
              required
            />
            <br />
            <button type="button" id="submitbtn">
              Login</button
            ><br />
          </div>
    
          <div class="container">
            <button type="button" id="cancelbtn">Cancel</button>
          </div>
        </form>
      </div>
    );
  }
}
