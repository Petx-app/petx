import axios from "axios";
import Cookies from "js-cookie";
import Router from "next/router";

const porta = process.env.NEXT_PUBLIC_BACK_APP_API_URL;

const api = axios.create({
  baseURL: porta,
});

api.interceptors.request.use(
  (config) => {
    const token = Cookies.get("auth");
    if (token) {
      config.headers["Authorization"] = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  },
);

api.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    if (error.response && error.response.status === 403) {
      Cookies.remove("auth");
      Router.push("/login");
    }
    return Promise.reject(error);
  },
);

export default api;
