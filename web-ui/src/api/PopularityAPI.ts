import APIUtil from "../util/APIUtil";

const baseURL = "/api/popularity";

const PopularityAPI = {
  getTop100: () =>
    APIUtil.request({
      method: "GET",
      baseURL,
      url: "/top-100",
    }),
};

export default PopularityAPI;
