import React, { Component } from "react";
import { ErrorMessage, Field, Form, Formik } from "formik";
import NewsDataService from "../api/news/NewsDataService.js";
import moment from "moment";
import { withTranslation } from "react-i18next";

class NewsComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      id: this.props.match.params.id,
      title: "",
      briefContent: "",
      content: "",
      date: moment(new Date()).format("YYYY-MM-DD"),
    };

    this.onSubmit = this.onSubmit.bind(this);
    this.validate = this.validate.bind(this);
  }

  componentDidMount() {
    if (this.state.id === -1) {
      return;
    }

    NewsDataService.retrieveNews(this.state.id).then((response) =>
      this.setState({
        title: response.data.title,
        briefContent: response.data.briefContent,
        content: response.data.content,
        date: moment(response.data.date).format("YYYY-MM-DD"),
      })
    );
  }

  validate(values) {
    let errors = {};
    if (!values.title) {
      errors.title =
        this.props.t("message.blank_field") + this.props.t("label.title");
    } else if (values.title.length < 5) {
      errors.title =
        this.props.t("message.not_enough_chars") + this.props.t("label.title");
    }

    if (!values.briefContent) {
      errors.title =
        this.props.t("message.blank_field") +
        this.props.t("label.brief_content");
    }

    if (!values.content) {
      errors.title =
        this.props.t("message.blank_field") + this.props.t("label.content");
    }

    if (!values.date) {
      errors.title =
        this.props.t("message.blank_field") + this.props.t("label.date");
    }

    console.log(values);
    return errors;
  }

  onSubmit(values) {
    console.log(this.state.id);
    let theNews = {
      id: this.state.id,
      title: values.title,
      briefContent: values.briefContent,
      content: values.content,
      date: values.date,
    };

    if (this.state.id === -1) {
      NewsDataService.updateNews(theNews).then(() =>
        this.props.history.push("/news")
      );
    } else {
      NewsDataService.updateNews(this.state.id, theNews).then(() =>
        this.props.history.push("/news")
      );
    }
  }

  render() {
    let { title, briefContent, content, date } = this.state;

    return (
      <div>
        <h1>{this.props.t("nav.news")}</h1>
        <div className="container">
          <Formik
            initialValues={{ title, briefContent, content, date }}
            onSubmit={this.onSubmit}
            validate={this.validate}
            enableReinitialize={true}
          >
            {(props) => (
              <Form>
                <ErrorMessage
                  name="title"
                  component="div"
                  className="alert alert-warning"
                />
                <ErrorMessage
                  name="briefContent"
                  component="div"
                  className="alert alert-warning"
                />
                <ErrorMessage
                  name="content"
                  component="div"
                  className="alert alert-warning"
                />
                <ErrorMessage
                  name="date"
                  component="div"
                  className="alert alert-warning"
                />
                <fieldset className="form-group">
                  <label>{this.props.t("label.title")}</label>
                  <Field className="form-control" type="text" name="title" />
                </fieldset>
                <fieldset className="form-group">
                  <label>{this.props.t("label.brief_content")}</label>
                  <Field
                    className="form-control"
                    type="text"
                    name="briefContent"
                  />
                </fieldset>
                <fieldset className="form-group">
                  <label>{this.props.t("label.content")}</label>
                  <Field className="form-control" type="text" name="content" />
                </fieldset>
                <fieldset className="form-group">
                  <label>{this.props.t("label.date")}</label>
                  <Field className="form-control" type="date" name="date" />
                </fieldset>
                <button className="btn btn-success" type="submit">
                  {this.props.t("label.save")}
                </button>
              </Form>
            )}
          </Formik>
        </div>
      </div>
    );
  }
}

export default withTranslation()(NewsComponent);
