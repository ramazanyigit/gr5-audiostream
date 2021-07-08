import { useEffect, useState } from "react";
import { Container, Row, Col, Table, Button } from "react-bootstrap";
import StreamingAPI from "../api/StreamingAPI";
import DurationUtil from "../util/DurationUtil";
import Playlists from "./Playlists";
import SearchSong from "./SearchSong";

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

export default function Home() {
  const [songs, setSongs] = useState(undefined as Song[] | undefined);
  const [message, setMessage] = useState(undefined as undefined | string);

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
  }, []);

  return (
    <Container fluid className="pt-3">
      <Row>
        <Col lg="8">
          <h4 className="mb-3 font-weight-light">LAST PLAYED</h4>
          {!songs?.length && <>{message ?? "There is no songs!"}</>}
          {songs?.length && (
            <Table striped hover size="sm">
              <thead>
                <tr className="border-none">
                  <th></th>
                  {/* <th>#</th> */}
                  <th>Name</th>
                  <th>Album</th>
                  <th>Artist</th>
                  <th>
                    <i className="fa fa-clock"></i>
                  </th>
                </tr>
              </thead>
              {songs?.map((song, idx) => (
                <tr>
                  <td>
                    <i className="text-primary fa fa-play-circle"></i>
                  </td>
                  {/* <td>{idx + 1}</td> */}
                  <td>{song.name}</td>
                  <td>{song.album?.name ?? "Unknown"}</td>
                  <td>{song.album?.artist?.name ?? "Unknown"}</td>
                  <td>
                    {DurationUtil.numberToHumanReadable(
                      song.duration as unknown as number | undefined
                    )}
                  </td>
                </tr>
              ))}
            </Table>
          )}

          <SearchSong />
        </Col>
        <Col lg="4">
          <Playlists />
        </Col>
      </Row>
    </Container>
  );
}
