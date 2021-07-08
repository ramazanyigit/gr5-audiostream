import APIUtil from "../util/APIUtil";

const baseURL = "/api/streaming";

const StreamingAPI = {
  getLastPlayed: () =>
    APIUtil.request({
      method: "GET",
      baseURL,
      url: "/last-played",
    }),
  
  // getSongsByName: (name: string) =>
  //   APIUtil.request({
  //     method: "GET",
  //     baseURL,
  //     url: "/song",
  //     params: {
  //       name,
  //     },
  //   }),
};

export default StreamingAPI;
