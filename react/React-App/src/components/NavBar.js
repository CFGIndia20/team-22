import React, { Component } from 'react';
import { Link } from 'react-router-dom';

export default class Navbar extends Component {

  render() {
    return (
      <nav className="navbar navbar-dark bg-dark navbar-expand-lg">
        <Link to="/" className="navbar-brand">Umeed Admin Dashboard</Link>
        <div className="collpase navbar-collapse">
        <ul className="navbar-nav mr-auto">
          <li className="navbar-item">
          <Link to="/tasks" className="nav-link">Tasks</Link>
          </li>
          <li className="navbar-item">
          <Link to="/events" className="nav-link">Events</Link>
          </li>
          <li className="navbar-item">
          <Link to="/Login" className="nav-link">Sign Out</Link>
          </li>
        </ul>
        </div>
      </nav>
    );
  }
}