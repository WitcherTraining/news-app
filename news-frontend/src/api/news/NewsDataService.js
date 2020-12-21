import axios from "axios";
import { API_URL } from "../../Constants.js";

class NewsDataService {
  retrieveAllNews() {
    return axios.get(`${API_URL}/api/news`);
  }

  retrieveNews(id) {
    return axios.get(`${API_URL}/api/news/${id}`);
  }

  showNews(id) {
    return axios.get(`${API_URL}/api/news/${id}/view`);
  }

  createNews(theNews) {
    return axios.post(`${API_URL}/api/news`, theNews);
  }

  updateNews(id, theNews) {
    return axios.put(`${API_URL}/api/news/${id}`, theNews);
  }

  deleteNews(id) {
    return axios.delete(`${API_URL}/api/news/${id}`);
  }

  deleteNewsList(data) {
    return axios.put(`${API_URL}/api/news`, data);
  }
}

export default new NewsDataService();
