import APIUtil from "../util/APIUtil";

const baseURL = "/api/collection";

const CollectionAPI = {
  getSongs: () =>
    APIUtil.request({
      method: "GET",
      baseURL,
      url: "/song",
    }),
  getSongsByName: (name: string) =>
    APIUtil.request({
      method: "GET",
      baseURL,
      url: "/song",
      params: {
        name,
      },
    }),
};

export default CollectionAPI;
