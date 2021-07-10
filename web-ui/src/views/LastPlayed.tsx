import { useState, useEffect } from "react";
import StreamingAPI from "../api/StreamingAPI";
import SongRow from "./SongRow";

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

export default function LastPlayed() {
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
    <>
      <h4 className="mb-3 font-weight-light">LAST PLAYED</h4>
      {!songs?.length && <>{message ?? "There is no songs!"}</>}
      {songs?.length && songs?.map((song, idx) => <SongRow key={idx} data={song} />)}
    </>
  );
}
