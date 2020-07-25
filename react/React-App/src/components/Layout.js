import React, { Component } from 'react';
import { BrowserRouter as Router, Route} from "react-router-dom";
import Navbar from './NavBar';
import Tasks from "./Tasks";
import Events from "./Events";
import LoginPage from "./LoginPage"

export default class Layout extends Component {
  render() {
    return (
      <div>
          <Router>
            <Navbar />
            <Route path="/tasks" exact component={Tasks} />
            <Route path="/events" component={Events} />
            {/* <Route path="/Login" component={LoginPage} /> */}
          </Router>
      </div>
    );
  }
}
