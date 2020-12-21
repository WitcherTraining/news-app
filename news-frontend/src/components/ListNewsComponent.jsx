import React, { Component } from "react";
import NewsDataService from "../api/news/NewsDataService.js";
import moment from "moment";
import { withTranslation } from 'react-i18next';

class ListNewsComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      news: [],
      message: null,
      idsForDeletion: []
    };

    this.refreshNews = this.refreshNews.bind(this);
    this.updateTheNewsClicked = this.updateTheNewsClicked.bind(this);
    this.addTheNewsClicked = this.addTheNewsClicked.bind(this);
    this.showTheNewsClicked = this.showTheNewsClicked.bind(this);
    this.handleInputForDeletion = this.handleInputForDeletion.bind(this);
    this.deleteCheckedNews = this.deleteCheckedNews.bind(this);
  }

  componentDidMount() {
    NewsDataService.retrieveAllNews().then((response) => {
      this.setState({ news: response.data });
    });
    console.log(this.state);
  }

  refreshNews() {
    NewsDataService.retrieveAllNews().then((response) => {
      this.setState({ news: response.data });
    });
  }

  handleInputForDeletion(event) {
    const target = event.target;
    var value = target.value;
    
    if(target.checked){
        this.state.idsForDeletion[value] = value;
    }
    else{
        this.state.idsForDeletion.splice(value, 1);
    }
  }

  deleteCheckedNews() {
    NewsDataService.deleteNewsList(this.state.idsForDeletion.filter(Number))
    .then(response => {
      console.log(response.data);
      this.props.history.push('/news')
    })
    .catch(e => {
      console.log(e);
    });
  }

  addTheNewsClicked() {
    this.props.history.push(`/news/0`);
  }

  updateTheNewsClicked(id) {
    this.props.history.push(`/news/${id}`);
  }

  showTheNewsClicked(id) {
    this.props.history.push(`/news/${id}/view`);
  }

  render() {
    return (
      <div>
        <hr />
        <h1>{this.props.t('label.breaking_news')}</h1>
        <hr />
        {this.state.message && (
          <div className="alert alert-success">{this.state.message}</div>
        )}
        <div className="container">
          {this.state.news.map((theNews) => (
            // col-12 col-sm-12 col-md-4 col-lg-8
            <div key={theNews.id} className="container-fluid">
              <h3>{theNews.title}</h3>
              <div className="text-right font-italic">
                {moment(theNews.date).format("YYYY-MM-DD")}
              </div>
              <br />
              <h4>{theNews.briefContent}</h4>
              <br />
              <button
                className="btn btn-success"
                onClick={() => this.showTheNewsClicked(theNews.id)}
              >
                {this.props.t('label.show_news')}
              </button>
              <div className="form-check text-left">
                <input
                  type="checkbox"
                  value={theNews.id} 
                  onChange={this.handleInputForDeletion}
                  className="form-check-input"
                  id="checkNewsForDelete"
                />
                <label
                  className="form-check-label col-auto mr-auto"
                  htmlFor="checkNewsForDelete"
                >
                  {this.props.t('message.delete_checked')}
                </label>
                <button
                  className="btn btn-success"
                  onClick={() => this.updateTheNewsClicked(theNews.id)}
                >
                  {this.props.t('label.update')}
                </button>
              </div>
              <hr />
            </div>
          ))}
          <div className="row">
            <button
              className="btn btn-success"
              onClick={this.addTheNewsClicked}
            >
              {this.props.t('label.add_news')}
            </button>
            <div className="container">
              <button className="btn btn-danger">{this.props.t('label.delete_checked_news')}</button>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default withTranslation()(ListNewsComponent);
