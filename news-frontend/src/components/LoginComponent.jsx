import React, { Component } from "react";
import AuthenticationService from "./AuthenticationService.js";
import { withTranslation } from "react-i18next";

class LoginComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: "admin",
      password: "admin",
      hasLoginFailed: false,
      showSuccessMessage: false,
    };
    // this.handleUsernameChange = this.handleUsernameChange.bind(this)
    // this.handlePasswordChange = this.handlePasswordChange.bind(this)
    this.handleChange = this.handleChange.bind(this);
    this.loginClicked = this.loginClicked.bind(this);
  }

  render() {
    return (
      <div>
        <h1>{this.props.t("nav.login")}</h1>
        <div className="container">
          {/* <ShowInvalidCredentials hasLoginFailed={this.state.hasLoginFailed}/> */}
          {this.state.hasLoginFailed && (
            <div className="alert alert-warning">
              {this.props.t("message.login_fail")}
            </div>
          )}
          {this.state.showSuccessMessage && (
            <div>{this.props.t("message.login_success")}</div>
          )}
          {/* <ShowSuccessMessage showSuccessMessage={this.state.showSuccessMessage}/> */}
          {this.props.t("label.username")}{" "}
          <input
            type="text"
            name="username"
            value={this.state.username}
            onChange={this.handleChange}
          ></input>
          {this.props.t("label.password")}{" "}
          <input
            type="password"
            name="password"
            value={this.state.password}
            onChange={this.handleChange}
          ></input>{" "}
          <button className="btn btn-success" onClick={this.loginClicked}>
            {this.props.t("nav.login")}
          </button>
        </div>
      </div>
    );
  }

  loginClicked() {
    AuthenticationService.executeJwtAuthenticationService(
      this.state.username,
      this.state.password
    )
      .then((response) => {
        AuthenticationService.registerSuccessfulLoginForJwt(
          this.state.username,
          response.data.token
        );
        this.props.history.push(`/welcome/${this.state.username}`);
      })
      .catch(() => {
        this.setState({ showSuccessMessage: false });
        this.setState({ hasLoginFailed: true });
      });
  }

  handleChange(event) {
    this.setState({
      [event.target.name]: event.target.value,
    });
  }
}

export default withTranslation()(LoginComponent);
