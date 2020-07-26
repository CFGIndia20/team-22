import React, { Component } from 'react';
import "bootstrap/dist/css/bootstrap.min.css";
import Axios from 'axios';
import {Form, Button} from "react-bootstrap";

export default class Events extends Component {

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
    const task = {
        contact: this.state.contact,
        password: this.state.password
    }

    Axios.post(`url`, task)
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
    return (
      <div>
         Hello World
    </div>
    );
  }
}
