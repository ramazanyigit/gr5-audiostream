import Axios, { AxiosRequestConfig } from "axios";

let authToken = "";

const APIUtil = {
  setAuthToken: (token?: string) => {
    authToken = token || "";
  },
  request: (options: AxiosRequestConfig) =>
    Axios({
      withCredentials: true,
      ...options,
      headers: {
        ...options.headers,
        Authorization: `Bearer ${authToken}`,
      },
    }),
};

export default APIUtil;
