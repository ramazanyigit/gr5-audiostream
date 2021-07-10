import { view } from "@risingstack/react-easy-state";
import { Row, Col } from "react-bootstrap";
import LastPlayed from "./LastPlayed";
import Playlists from "./Playlists";
import streamingStore from "../store/streamingStore";
import { useEffect } from "react";

function Home() {
  const { update } = streamingStore;

  useEffect(() => {
    update();
  }, [update]);
  return (
    <div>
      <Row>
        <Col lg="4">
          <Playlists />
        </Col>
        <Col lg="8">
          <LastPlayed />
        </Col>
      </Row>
    </div>
  );
}

export default view(Home);
