import React, { Component } from "react";
import { Link } from "react-router-dom";
import { withRouter } from "react-router";
import AuthenticationService from "./AuthenticationService.js";
import App from "../App.js";

class HeaderComponent extends Component {

  render() {
    const isUserLoggedIn = AuthenticationService.isUserLoggedIn();
    console.log(isUserLoggedIn);

    return (
      <header>
        <nav className="navbar navbar-expand-md navbar-dark bg-dark">
          <div>
            <a href="http://www.epam.com" className="navbar-brand">
              EPAM
            </a>
          </div>
          <ul className="navbar-nav">
            {/* if isUserLoggedIn = true, link become visible */}
            {isUserLoggedIn && (
              <li>
                <Link className="nav-link" to="/welcome/witcher">
                  Home
                </Link>
              </li>
            )}
            {isUserLoggedIn && (
              <li>
                <Link className="nav-link" to="/news">
                  News
                </Link>
              </li>
            )}
          </ul>
          <div className="container">
            <ul className="navbar-nav">
              <li>
                <Link className="nav-link" to="/news">
                  Russian
                </Link>
              </li>
              <li>
                <Link className="nav-link" to="/news">
                  English
                </Link>
              </li>
            </ul>
          </div>
          <ul className="navbar-nav navbar-collapse justify-content-end">
            {!isUserLoggedIn && (
              <li>
                <Link className="nav-link" to="/login">
                  Login
                </Link>
              </li>
            )}
            {isUserLoggedIn && (
              <li>
                <Link
                  className="nav-link"
                  to="/logout"
                  onClick={AuthenticationService.logout}
                >
                  Logout
                </Link>
              </li>
            )}
          </ul>
        </nav>
      </header>
    );
  }
}

export default withRouter(HeaderComponent);
