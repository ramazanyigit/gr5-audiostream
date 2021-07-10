import { view } from "@risingstack/react-easy-state";
import { useEffect, useState } from "react";
import { Row, Col } from "react-bootstrap";
import RecommendationAPI from "../api/RecommendationAPI";
import streamingStore from "../store/streamingStore";
import { Song } from "../util/types";
import SongRow from "./SongRow";

function SongRecommendation() {
    const [recommendation, setRecommendation] = useState(undefined as undefined | Song);
    const { currentPlaying } = streamingStore;

    useEffect(() => {
        if (!currentPlaying) {
            setRecommendation(undefined);
            return;
        }

        RecommendationAPI.recommendSong(currentPlaying.songId).then(({ data }) => {
            setRecommendation(data);
        }).catch(() => setRecommendation(undefined));
    }, [currentPlaying]);

    if (!recommendation) {
        return <></>;
    }

    return <Row className="mb-4">
        <Col lg="8">
            <h5>RECOMMENDATED SONG</h5>
            <SongRow data={recommendation} />
        </Col>
    </Row>;
}

export default view(SongRecommendation);