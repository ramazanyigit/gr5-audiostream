import { Row, Col } from "react-bootstrap";
import styled from "styled-components";
import StreamingAPI from "../api/StreamingAPI";
import DurationUtil from "../util/DurationUtil";
import streamingStore from "../store/streamingStore";
import { view } from "@risingstack/react-easy-state";

interface SongRowProps {
  data: {
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
  };
  actions?: React.ReactNode;
}

const RowContainer = styled.div`
  border: 1px solid #ccc;
  padding: 0.5rem 1rem;
  margin-bottom: 0.25rem;
`;

const HoverableIcon = styled.i`
  &:hover {
    color: #007bff !important;
    font-size: 1rem;
    cursor: pointer;
  }
`;

function SongRow({ data, actions }: SongRowProps) {
  const { update } = streamingStore;
  return (
    <RowContainer>
      <Row>
        <Col xs="auto">
          <HoverableIcon
            className="text-dark fas fa-1x fa-play-circle"
            onClick={() =>
              StreamingAPI.play({
                songId: data.id,
                playOffset: 0,
              }).finally(() => {
                update();
              })
            }
          ></HoverableIcon>
        </Col>
        <Col>{data.name}</Col>
        <Col>
          <i className="fas fa-record-vinyl mr-2"></i>
          <span>{data.album?.name ?? "Unknown"}</span>
        </Col>
        <Col>
          <i className="fas fa-user-tag mr-2"></i>
          <span>{data.album?.artist?.name ?? "Unknown"}</span>
        </Col>
        <Col xs="auto">
          <i className="fa fa-clock mr-2"></i>{" "}
          <span>{DurationUtil.numberToHumanReadable(data.duration)}</span>
        </Col>
        {actions && <Col xs="auto">{actions}</Col>}
      </Row>
    </RowContainer>
  );
}

export default view(SongRow);
