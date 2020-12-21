import React, { Component } from "react";
import { Link } from "react-router-dom";
import { withRouter } from "react-router";
import AuthenticationService from "./AuthenticationService.js";
import { useTranslation } from "react-i18next";

function HeaderComponento(props) {
  const { t, i18n } = useTranslation();

  const changeLanguage = (language) => {
    i18n.changeLanguage(language);
  };

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
                {t("nav.home")}
              </Link>
            </li>
          )}
          {isUserLoggedIn && (
            <li>
              <Link className="nav-link" to="/news">
                {t("nav.news")}
              </Link>
            </li>
          )}
        </ul>
        <div className="container">
          <ul className="navbar-nav">
            <div className="container">
              <button
                onClick={() => changeLanguage("en")}
                className="btn btn-info"
              >
                EN
              </button>
            </div>
            <button
              onClick={() => changeLanguage("ru")}
              className="btn btn-info"
            >
              RU
            </button>
          </ul>
        </div>
        <ul className="navbar-nav navbar-collapse justify-content-end">
          {!isUserLoggedIn && (
            <li>
              <Link className="nav-link" to="/login">
                {t("nav.login")}
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
                {t("nav.logout")}
              </Link>
            </li>
          )}
        </ul>
      </nav>
    </header>
  );
}

export default withRouter(HeaderComponento);
