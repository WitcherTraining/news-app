import React, { Component } from "react";
import { Link } from "react-router-dom";
import HelloWorldService from "../api/news/NewsDataService.js";
import { withTranslation } from "react-i18next";

class WelcomeComponent extends Component {
  constructor(props) {
    super(props);
    this.retrieveWelcomeMessage = this.retrieveWelcomeMessage.bind(this);
    this.state = {
      welcomeMessage: "",
    };
    this.handleSuccessfulResponse = this.handleSuccessfulResponse.bind(this);
    this.handleError = this.handleError.bind(this);
  }

  render() {
    return (
      <>
        <h1>Welcome!</h1>
        <div className="container">
          {" "}
          {this.props.t("message.welcome_msg_part1")} 
          {this.props.match.params.name}
          {this.props.t("message.welcome_msg_part2")}
          <Link to="/news">{this.props.t("message.welcome_msg_part3")}</Link>
        </div>
        <div className="container">{this.state.welcomeMessage}</div>

        <div class="container">
          <div class="row">
            <div class="col-12">
              <h1>Общий заголовок для страницы</h1>
            </div>
          </div>
        </div>
        <div class="container">
          <div class="row">
            <div class="col-12 col-sm-12 col-md-4 col-lg-2 col-xl-2">
              Это боковая панель, здесь будет установка языка и текущий
              пользователь
            </div>
            <div class="col-12 col-sm-12 col-md-4 col-lg-8 col-xl-8">
              <h3>Заголовок новости</h3>
              <h4>
                Давайте будем честными, мы давно не выходили на контакт...не
                постили новые выпуски, не подавали признаков активности, да и
                вообще синхронной коммуникации никто не обещал, благо на этом
                мир не кончается и существует масса способов связаться с нами,
                например послать сообщение, естественно через хороший и надежный
                инструмент... <br />
                <Link to="/news">Read here</Link>
              </h4>
              <div class="form-check text-left">
                <input
                  type="checkbox"
                  class="form-check-input"
                  id="checkNewsForDelete"
                />
                <label class="form-check-label" for="checkNewsForDelete">
                  Choose for delete
                </label>
              </div>
            </div>
          </div>
        </div>
      </>
    );
  }

  retrieveWelcomeMessage() {
    HelloWorldService.executeHelloWorldPathVariableService(
      this.props.match.params.name
    )
      .then((response) => this.handleSuccessfulResponse(response))
      .catch((error) => this.handleError(error));
  }

  handleSuccessfulResponse(response) {
    console.log(response);
    this.setState({ welcomeMessage: response.data.message });
  }

  handleError(error) {
    let errorMessage = "";
    if (error.message) {
      errorMessage += error.message;
    }

    if (error.response && error.response.data) {
      errorMessage += error.response.data.message;
    }

    console.log(error.response);
    this.setState({ welcomeMessage: errorMessage });
  }
}

export default withTranslation()(WelcomeComponent);
