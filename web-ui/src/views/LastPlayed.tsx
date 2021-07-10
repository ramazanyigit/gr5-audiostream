import { useState, useEffect } from "react";
import { Col, Row } from "react-bootstrap";
import OrgSwal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";
import SongRow from "./SongRow";
import PlaylistAPI from "../api/PlaylistAPI";
import StreamingAPI from "../api/StreamingAPI";
import { HoverableRowContainer } from "./BaseComponents";
import { Playlist } from "../util/types";
import streamingStore from "../store/streamingStore";
import { view } from "@risingstack/react-easy-state";

const Swal = withReactContent(OrgSwal);

interface Song {
  id: string;
  name: string;
  duration: number;
  album: {
    id: string;
    name: string;
    year: number;
    genre: string;
    artist: {
      id: string;
      name: string;
    };
  };
}

function LastPlayed() {
  const [songs, setSongs] = useState(undefined as Song[] | undefined);
  const [message, setMessage] = useState(undefined as undefined | string);
  const { currentPlaying } = streamingStore;

  useEffect(() => {
    StreamingAPI.getLastPlayed()
      .then(({ data }) => {
        setSongs(data);
        setMessage(undefined);
      })
      .catch(() => {
        setMessage(
          "Cannot fetch last played songs from service. Please try again later."
        );
      });
  }, [currentPlaying]);

  return (
    <>
      <h4 className="mb-3 font-weight-light">LAST PLAYED</h4>
      {!songs?.length && <>{message ?? "There is no songs!"}</>}
      {(songs?.length ?? 0) >= 0 && songs?.map((song, idx) => <SongRow key={idx} data={song} actions={<><i
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
      ></i></>} />)}
    </>
  );
}

export default view(LastPlayed);