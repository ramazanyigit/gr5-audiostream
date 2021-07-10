import { useState, useRef, useEffect } from "react";
import { view } from "@risingstack/react-easy-state";
import classNames from "classnames";
import moment from "moment";
import { Col, Row } from "react-bootstrap";

import StreamingAPI from "../api/StreamingAPI";
import streamingStore from "../store/streamingStore";
import DurationUtil from "../util/DurationUtil";
import { HoverableIcon } from "./BaseComponents";

function Player() {
  const timeoutRef = useRef(undefined as number | undefined);
  const [playingOffset, setPlayingOffset] = useState(0);
  const { currentPlaying, update } = streamingStore;

  useEffect(() => {
    clearInterval(timeoutRef.current);
    if (!currentPlaying) {
      setPlayingOffset(0);
      return;
    }

    timeoutRef.current = window.setInterval(() => {
      const newPlayingOffset = (currentPlaying?.playOffset ?? 0) +
        moment().diff(
          moment(currentPlaying?.creationTimestamp),
          "seconds",
          true
        );
      setPlayingOffset(
        newPlayingOffset > 0 ? newPlayingOffset : 0
      );

      if (newPlayingOffset >= currentPlaying?.song.duration) {
        update();
      }
    }, 1000);
  }, [currentPlaying, update]);
  return (
    <div className="bg-light px-3 py-2 border-bottom">
      <Row>
        <Col xs="auto" className="d-flex align-items-center">
          <HoverableIcon
            className={classNames({
              fas: true,
              "fa-step-backward": true,
            })}
            style={{ width: "1.5em" }}
          />
          <HoverableIcon
            className={classNames({
              fas: true,
              "fa-stop": currentPlaying,
              "fa-play": !currentPlaying,
            })}
            onClick={() => {
              if (!currentPlaying) {
                return;
              }

              StreamingAPI.stopCurrent().finally(() => {
                update();
              });
            }}
            style={{ width: "1.5em", textAlign: "center" }}
          />
          <HoverableIcon
            className={classNames({
              fas: true,
              "fa-step-forward": true,
            })}
            style={{ width: "1.5em", textAlign: "right" }}
          />
        </Col>
        <Col>
          <div>
            {currentPlaying?.song?.name ?? <i>A song is not playing.</i>}
          </div>
          <div className="small">
            {currentPlaying?.song?.album?.name ?? "Unknown"},{" "}
            {currentPlaying?.song?.album?.artist?.name ?? "Unknown"}
          </div>
        </Col>
        <Col xs="auto" className="d-flex align-items-center">
          {DurationUtil.numberToHumanReadable(playingOffset)}/
          {DurationUtil.numberToHumanReadable(
            currentPlaying?.song?.duration ?? 0
          )}
        </Col>
      </Row>
    </div>
  );
}

export default view(Player);
