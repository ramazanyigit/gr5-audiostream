import APIUtil from "../util/APIUtil";

const baseURL = "/api/streaming";

const StreamingAPI = {
  getLastPlayed: () =>
    APIUtil.request({
      method: "GET",
      baseURL,
      url: "/last-played",
    }),
  getCurrentPlaying: () =>
    APIUtil.request({
      method: "GET",
      baseURL,
      url: "/current-playing",
    }),
  play: (data: { songId: string; playOffset: number }) =>
    APIUtil.request({
      method: "POST",
      baseURL,
      url: "/play",
      data,
    }),
  stopCurrent: () =>
    APIUtil.request({
      method: "POST",
      baseURL,
      url: "/stop-current",
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
