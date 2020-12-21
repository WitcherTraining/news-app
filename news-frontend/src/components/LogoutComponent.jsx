import React, { Component } from "react";
import { withTranslation } from 'react-i18next';

class LogoutComponent extends Component {
  render() {
    return (
      <div>
        <h1>{this.props.t("message.logout_msg")}</h1>
        <div className="container">{this.props.t("message.thanks")}</div>
      </div>
    );
  }
}

export default withTranslation()(LogoutComponent);
