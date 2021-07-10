import APIUtil from "../util/APIUtil";

const baseURL = "/api/recommendation";

const RecommendationAPI = {
  recommendSong: (id: string) =>
    APIUtil.request({
      method: "GET",
      baseURL,
      url: "/song/" + id,
    }),
};

export default RecommendationAPI;
