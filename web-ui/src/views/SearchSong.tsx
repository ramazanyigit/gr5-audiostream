import { view } from "@risingstack/react-easy-state";
import { useEffect, useState } from "react";
import { Col, Row } from "react-bootstrap";
import OrgSwal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";
import CollectionAPI from "../api/CollectionAPI";
import PlaylistAPI from "../api/PlaylistAPI";
import StreamingAPI from "../api/StreamingAPI";
import streamingStore from "../store/streamingStore";
import { Playlist } from "../util/types";
import { HoverableRowContainer } from "./BaseComponents";

const Swal = withReactContent(OrgSwal);

let searchTimeoutRef: number | undefined = undefined;

interface Song {
  id: string;
  name: string;
  albumId: string;
  albumName: string;
  artistId: string;
  artistName: string;
  genre: string;
  year: number;
  duration: number;
}

function SearchSong() {
  const [message, setMessage] = useState("");
  const [searchText, setSearchText] = useState("");
  const [result, setResult] = useState([] as Song[]);
  const { currentPlaying, update } = streamingStore;

  useEffect(() => {
    const trimmedSearch = searchText.trim();
    clearTimeout(searchTimeoutRef);
    setMessage("");
    if (trimmedSearch.length === 0) {
      setResult([]);
      return;
    }

    searchTimeoutRef = setTimeout(() => {
      setMessage("Fetching...");
      CollectionAPI.getSongsByName(trimmedSearch)
        .then(({ data }) => {
          setResult(data);
          setMessage("");
        })
        .catch(() => {
          setMessage(
            "Cannot reach to the collection service. Please try again later."
          );
        });
    }, 500) as unknown as number;
  }, [searchText]);

  return (
    <div className="mb-5">
      <div className="row">
        <div className="col-12">
          <h4 className="mb-3 font-weight-light">SEARCH</h4>
        </div>

        <div className="col-6">
          <input
            type="text"
            className="form-control"
            value={searchText}
            onChange={(e) => setSearchText(e.target.value)}
          />
        </div>
      </div>
      {message && <div className="py-2">{message}</div>}
      <div className="row">
        {!message && searchText && result?.length <= 0 && (
          <div className="col-lg-6 py-2">
            No result matching the search criteria.
          </div>
        )}
        {result?.map((song) => (
          <div className="col-lg-6">
            <div
              className="border rounded py-2 px-4 my-2"
              style={{ background: "#fcfcfc" }}
            >
              <div className="row">
                <div className="col">
                  <div>
                    {song.name} ({song.albumName})
                  </div>
                  <div className="small">
                    {song.genre}, {song.year}
                  </div>
                  <div className="small">{song.artistName}</div>
                </div>
                <div className="col-auto d-flex justify-content-end align-items-center">
                  <i
                    className="btn btn-dark btn-circle fas fa-play mr-2"
                    onClick={() =>
                      song.id !== currentPlaying?.id &&
                      StreamingAPI.play({
                        songId: song.id,
                        playOffset: 0,
                      }).finally(() => {
                        update();
                      })
                    }
                  ></i>
                  <i
                    className="btn btn-success btn-circle fas fa-plus"
                    onClick={() => {
                      PlaylistAPI.getPlaylists()
                        .then(({ data }) => {
                          Swal.fire({
                            title: "Choose playlist",
                            html: (
                              <>
                                {data?.length > 0 &&
                                  data?.map((playlist: Playlist) => (
                                    <HoverableRowContainer
                                      onClick={() => {
                                        PlaylistAPI.addSong({
                                          playlistId: playlist.id,
                                          songId: song.id,
                                        })
                                          .then(() =>
                                            Swal.fire({
                                              title: "Success",
                                              icon: "success",
                                              text: "Song added successfully.",
                                            })
                                          )
                                          .catch(() =>
                                            Swal.fire({
                                              title: "Fail",
                                              icon: "error",
                                              text: "Song cannot be added to playlist. Service unavailable.",
                                            })
                                          );
                                      }}
                                    >
                                      <Row>
                                        <Col>{playlist.name}</Col>
                                        <Col>
                                          <i className="fas fa-music mr-2"></i>
                                          <span>
                                            {playlist.songs?.length ?? "0"}{" "}
                                            songs
                                          </span>
                                        </Col>
                                      </Row>
                                    </HoverableRowContainer>
                                  ))}
                              </>
                            ),
                            showConfirmButton: false,
                          });
                        })
                        .catch(() => {
                          Swal.fire({
                            icon: "error",
                            title: "Request failed.",
                            text: "Playlist services are unavailable right now. Please try again later.",
                          });
                        });
                    }}
                  ></i>
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default view(SearchSong);
