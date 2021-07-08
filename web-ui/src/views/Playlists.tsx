import { useEffect, useState } from "react";
import { Col, Row, Button, Table } from "react-bootstrap";
import PlaylistAPI from "../api/PlaylistAPI";
import PlaylistAddModal from "./PlaylistAddModal";

interface Playlist {
  id: string;
  name: string;
  userId: string;
  songs: Record<string, unknown>[];
}

export default function Playlists() {
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
      {playlists?.length > 0 && (
        <Table striped hover size="sm">
          <thead>
            <tr className="border-none">
              <th>Name</th>
              <th>Song Count</th>
              <th></th>
            </tr>
          </thead>
          {playlists?.map((playlist) => (
            <tr>
              <td>{playlist.name}</td>
              <td>{playlist.songs?.length}</td>
              <td className="text-right">
                <i className="btn btn-sm btn-dark btn-circle fas fa-play mr-2"></i>
                <i
                  className="btn btn-sm btn-danger btn-circle fas fa-times"
                  onClick={() => {
                    PlaylistAPI.deleteByIds([playlist.id]).finally(() =>
                      reloadData()
                    );
                  }}
                ></i>
              </td>
            </tr>
          ))}
        </Table>
      )}
    </>
  );
}
