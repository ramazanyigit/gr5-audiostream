import APIUtil from "../util/APIUtil";

const baseURL = "/api/playlist";

const PlaylistAPI = {
  getPlaylists: () =>
    APIUtil.request({
      method: "GET",
      baseURL,
      url: "/",
    }),
  getPlaylistById: (id: string) =>
    APIUtil.request({
      method: "GET",
      baseURL,
      url: `/${id}`,
    }),
  save: (data: { id?: string; name: string }) =>
    APIUtil.request({
      method: "POST",
      baseURL,
      url: `/`,
      data,
    }),
  deleteByIds: (ids: string[]) =>
    APIUtil.request({
      method: "DELETE",
      baseURL,
      url: "/",
      data: ids,
    }),
};

export default PlaylistAPI;
