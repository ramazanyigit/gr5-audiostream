import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import PlaylistAPI from "../api/PlaylistAPI";
import { Playlist } from "../util/types";
import { HoverableIcon } from "./BaseComponents";
import SongRow from "./SongRow";

export default function PlaylistDetail() {
  const { id } = useParams<{ id: string }>();
  const [playlist, setPlaylist] = useState(undefined as Playlist | undefined);
  const [message, setMessage] = useState(undefined as undefined | string);

  useEffect(() => {
    PlaylistAPI.getPlaylistById(id)
      .then(({ data }) => {
        setPlaylist(data);
        setMessage(undefined);
      })
      .catch(() => {
        setMessage(
          "Cannot fetch last played songs from service. Please try again later."
        );
      });
  }, [id]);

  return (
    <>
      <h4 className="mb-3 font-weight-light">
        <i className="fa fa-record-vinyl mr-2"></i>{" "}
        <span className="text-uppercase">{playlist?.name}</span>
      </h4>
      {!playlist?.songs?.length && <>{message ?? "There is no songs!"}</>}
      {(playlist?.songs?.length ?? 0) > 0 &&
        playlist?.songs?.map((song, idx) => (
          <SongRow
            key={idx}
            data={song.detail}
            actions={
              <>
                <HoverableIcon
                  className="fas fa-times"
                  onClick={(e) => {
                    e.stopPropagation();
                    PlaylistAPI.deleteSong(song.id).then(() =>
                      PlaylistAPI.getPlaylistById(id)
                        .then(({ data }) => {
                          setPlaylist(data);
                          setMessage(undefined);
                        })
                        .catch(() => {
                          setMessage(
                            "Cannot fetch last played songs from service. Please try again later."
                          );
                        })
                    );
                  }}
                />
              </>
            }
          />
        ))}
    </>
  );
}
