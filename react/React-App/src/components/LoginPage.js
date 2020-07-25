import React, { Component } from 'react';
import Axios from 'axios';
import Layout from './Layout';
import "./css/loginPage.css"

export default class LoginPage extends Component {

    constructor(props){

        super(props);

        this.state = {
            contact: '',
            password: '',
            auth: 0
        }
    }
    

    handleContactChange = event => {
        this.setState({contact: event.target.value});
        // console.log(event.target.value);
    }

    handlePasswordChange = event => {
        this.setState({password: event.target.value});
        // console.log(event.target.value);
    }

    handleSubmit = event => {
        event.preventDefault();
        // alert(this.state.contact , this.state.password);
        const user = {
            contact: this.state.contact,
            password: this.state.password
        }

        Axios.post(`https://sleepy-coast-63651.herokuapp.com/login_manage.php`, user)
        .then( res => {
            console.log(res);
            console.log(res.data);
            if(res.data.worker_id === "1"){
                this.setState({auth: 1});
            }
            else console.log("Not Admin");
        });
    };

    
    render() {

        if(this.state.auth){
            return (
            <Layout />
        )};

        return (
            <div className="loginBody">
            <form action="" method="">
            <div className="loginbox">
 
              <img src="./images/avatar.png" className="avatar" />
 
              <label htmlFor="uname"><b>Username</b></label
              ><br />
              <input
                type="text"
                placeholder="Enter Username"
                name="uname"
                id="uname"
                required
                onChange={this.handleContactChange}
              /><br />

              <label htmlFor="psw"><b>Password</b></label
              ><br />
              <input
                type="password"
                placeholder="Enter Password"
                name="psw"
                id="psw"
                required
                onChange={this.handlePasswordChange}
              />
              <br />
  
              <button type="button" id="submitbtn" onClick={this.handleSubmit}>Login</button>
              <br /><br />

              <button type="button" id="cancelbtn">Cancel</button>
            </div>
          </form>
          </div>

        );
  }
}
