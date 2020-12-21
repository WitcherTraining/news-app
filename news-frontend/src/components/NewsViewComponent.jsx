import React, { Component } from "react";
import NewsDataService from "../api/news/NewsDataService.js";
import moment from "moment";
import { Link } from "react-router-dom";
import { withTranslation } from 'react-i18next';

class NewsViewComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      id: this.props.match.params.id,
      title: "",
      briefContent: "",
      content: "",
      date: moment(new Date()).format("YYYY-MM-DD"),
    };

    this.deleteTheNewsClicked = this.deleteTheNewsClicked.bind(this);
    this.updateTheNewsClicked = this.updateTheNewsClicked.bind(this);
    this.addTheNewsClicked = this.addTheNewsClicked.bind(this);
  }

  componentDidMount() {
    NewsDataService.retrieveNews(this.state.id).then((response) =>
      this.setState({
        title: response.data.title,
        briefContent: response.data.briefContent,
        content: response.data.content,
        date: moment(response.data.date).format("YYYY-MM-DD"),
      })
    );
  }

  refreshNews() {
    <Link className="nav-link" to="/news">
      News
    </Link>;
  }

  deleteTheNewsClicked(id) {
    NewsDataService.deleteTheNews(id).then((response) => {
      this.setState({
        message: this.props.t("message.delete_success") + id,
      });
      this.refreshNews();
    });
  }

  addTheNewsClicked() {
    this.props.history.push(`/news/0`);
  }

  updateTheNewsClicked(id) {
    this.props.history.push(`/news/${id}`);
  }

  render() {
    return (
      <div>
        <br />
        <div className="container">
          <div key={this.state.id} class="container-fluid">
            <h1>{this.state.title}</h1>
            <div className="text-right font-italic">
              {moment(this.state.date).format("YYYY-MM-DD")}
            </div>
            <br />
            <div className="text-right font-italic">
              {this.state.briefContent}
            </div>
            <br />
            <h3 className="text-info text-left">{this.state.content}</h3>
            <div class="form-check text-right">
              <button
                className="btn btn-success"
                onClick={() => this.updateTheNewsClicked(this.state.id)}
              >
                {this.props.t("label.update")}
              </button>
            </div>
            <hr />
          </div>
          <div className="container">
            <div className="text-right">
              <button
                className="btn btn-success"
                onClick={this.addTheNewsClicked}
              >
                {this.props.t("label.add_news")}
              </button>
            </div>
            <button
              className="btn btn-danger"
              onClick={() => this.deleteTheNewsClicked(this.state.id)}
            >
              {this.props.t("label.delete_news")}
            </button>
          </div>
        </div>
      </div>
    );
  }
}

export default withTranslation()(NewsViewComponent);
