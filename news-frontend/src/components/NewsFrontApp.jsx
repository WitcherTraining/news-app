import React, { Component } from "react";
import { BrowserRouter as Router, Route, Switch} from "react-router-dom";
import AuthenticatedRoute from "./AuthenticatedRoute.jsx";
import LoginComponent from "./LoginComponent.jsx";
import ListNewsComponent from "./ListNewsComponent.jsx";
// import HeaderComponent from "./RedundantHeaderComponent.jsx";
import ErrorComponent from "./ErrorComponent.jsx";
import FooterComponent from "./FooterComponent.jsx";
import LogoutComponent from "./LogoutComponent.jsx";
import WelcomeComponent from "./WelcomeComponent.jsx";
import NewsComponent from "./NewsComponent.jsx";
import NewsViewComponent from "./NewsViewComponent.jsx";
import HeaderComponent from "./HeaderComponent.jsx";

class NewsFrontApp extends Component {
  render() {
    return (
      <div className="NewsFrontApp">
        <Router>
          <>
            <HeaderComponent />
            <Switch>
              <Route path="/" exact component={LoginComponent} />
              <Route path="/login" component={LoginComponent} />
              <AuthenticatedRoute
                path="/welcome/:name"
                component={WelcomeComponent}
              />
              <AuthenticatedRoute
                path="/news/:id/view"
                component={NewsViewComponent}
              />
              <AuthenticatedRoute
                path="/news/:id"
                component={NewsComponent}
              />
              <AuthenticatedRoute
                path="/news"
                component={ListNewsComponent}
              />
              <AuthenticatedRoute path="/logout" component={LogoutComponent} />
              <Route path="" component={ErrorComponent} />
            </Switch>
            <FooterComponent />
          </>
        </Router>
      </div>
    );
  }
}

export default NewsFrontApp;
