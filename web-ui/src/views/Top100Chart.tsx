import { useState, useEffect } from "react";
import { Col, Row } from "react-bootstrap";
import OrgSwal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";
import { useParams } from "react-router-dom";
import PopularityAPI from "../api/PopularityAPI";
import PlaylistAPI from "../api/PlaylistAPI";
import { Chart, Playlist } from "../util/types";
import { HoverableRowContainer } from "./BaseComponents";
import SongRow from "./SongRow";

const Swal = withReactContent(OrgSwal);

export default function Top100Chart() {
  const { id } = useParams<{ id: string }>();
  const [chart, setChart] = useState(undefined as Chart | undefined);
  const [message, setMessage] = useState(undefined as undefined | string);

  useEffect(() => {
    PopularityAPI.getTop100()
      .then(({ data }) => {
        setChart(data);
        setMessage(undefined);
      })
      .catch(() => {
        setMessage(
          "Cannot fetch chart from service. Please try again later."
        );
      });
  }, [id]);

  return (
    <>
      <h4 className="mb-3 font-weight-light">
        <i className="fa fa-record-vinyl mr-2"></i>{" "}
        <span className="text-uppercase">{chart?.name}</span>
      </h4>
      {!chart?.songs?.length && <>{message ?? "There is no songs!"}</>}
      {(chart?.songs?.length ?? 0) > 0 &&
        chart?.songs?.map((song, idx) => (
          <SongRow
            key={idx}
            data={song}
            actions={<><i
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
            ></i></>}
          />
        ))}
    </>
  );
}
