import { useEffect, useState } from "react";
import { Col, Row, Button } from "react-bootstrap";
import { useHistory } from "react-router-dom";

import { HoverableIcon, HoverableRowContainer } from "./BaseComponents";
import PlaylistAPI from "../api/PlaylistAPI";
import PlaylistAddModal from "./PlaylistAddModal";

interface Playlist {
  id: string;
  name: string;
  userId: string;
  songs: Record<string, unknown>[];
}

export default function Playlists() {
  const { push } = useHistory();
  const [playlists, setPlaylists] = useState([] as Playlist[]);
  const [message, setMessage] = useState(undefined as undefined | string);
  const [showModal, setShowModal] = useState(false);

  const reloadData = () =>
    PlaylistAPI.getPlaylists().then(({ data }) => {
      setPlaylists(data);
    });

  useEffect(() => {
    setMessage("Fetching data...");
    PlaylistAPI.getPlaylists()
      .then(({ data }) => {
        setPlaylists(data);
        setMessage(undefined);
      })
      .catch(() => {
        setMessage(
          "Cannot fetch the data from service. Please try again later."
        );
      });
  }, []);

  return (
    <>
      <PlaylistAddModal
        show={showModal}
        handleClose={() => setShowModal(false)}
        onSubmit={(data) => {
          PlaylistAPI.save(data).then(() => {
            reloadData();
            setShowModal(false);
          });
        }}
      />
      <Row>
        <Col>
          <h4 className="mb-3 font-weight-light">PLAYLISTS</h4>
        </Col>
        <Col xs="auto">
          <Button size="sm" variant="dark" onClick={() => setShowModal(true)}>
            <i className="fa fa-plus-circle mr-2"></i> Create a playlist
          </Button>
        </Col>
      </Row>
      {message && <div className="py-2">{message}</div>}
      {playlists?.length <= 0 && !message && <>{"There is no playlist!"}</>}
      {playlists?.length > 0 &&
        playlists?.map((playlist) => (
          <HoverableRowContainer
            onClick={() => push(`/playlist/${playlist.id}`)}
          >
            <Row>
              <Col xs="auto">
                <HoverableIcon
                  className="text-dark fas fa-1x fa-play-circle"
                  onClick={(e) => {
                    e.stopPropagation();
                  }}
                ></HoverableIcon>
              </Col>
              <Col>{playlist.name}</Col>
              <Col>
                <i className="fas fa-music mr-2"></i>
                <span>{playlist.songs?.length ?? "0"} songs</span>
              </Col>
              <Col xs="auto">
                <HoverableIcon
                  className="fas fa-times"
                  onClick={() => {
                    PlaylistAPI.deleteByIds([playlist.id]).finally(() =>
                      reloadData()
                    );
                  }}
                ></HoverableIcon>
              </Col>
            </Row>
          </HoverableRowContainer>
        ))}
    </>
  );
}
