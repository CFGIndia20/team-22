import React, { Component } from 'react';
import { BrowserRouter as Router, Route} from "react-router-dom";
import Navbar from './NavBar';
import Tasks from "./Tasks";
import Events from "./Events";

export default class Layout extends Component {
  render() {
    return (
      <div>
          <Router>
            <Navbar />
            <Route path="/tasks" exact component={Tasks} />
            <Route path="/events" component={Events} />
          </Router>
      </div>
    );
  }
}
