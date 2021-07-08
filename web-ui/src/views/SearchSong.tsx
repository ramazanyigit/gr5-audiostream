import { useEffect, useState } from "react";
import CollectionAPI from "../api/CollectionAPI";

let searchTimeoutRef: number | undefined = undefined;

interface Song {
  id: string;
  name: string;
  albumId: string;
  albumName: string;
  artistId: string;
  artistName: string;
  genre: string;
  year: number;
  duration: number;
}

export default function SearchSong() {
  const [message, setMessage] = useState("");
  const [searchText, setSearchText] = useState("");
  const [result, setResult] = useState([] as Song[]);

  useEffect(() => {
    const trimmedSearch = searchText.trim();
    clearTimeout(searchTimeoutRef);
    setMessage("");
    if (trimmedSearch.length === 0) {
      setResult([]);
      return;
    }

    searchTimeoutRef = setTimeout(() => {
      setMessage("Fetching...");
      CollectionAPI.getSongsByName(trimmedSearch)
        .then(({ data }) => {
          setResult(data);
          setMessage("");
        })
        .catch(() => {
          setMessage(
            "Cannot reach to the collection service. Please try again later."
          );
        });
    }, 500) as unknown as number;
  }, [searchText]);

  return (
    <div className="mt-5">
      <div className="row">
        <div className="col-12">
          <h6 className="mb-3 font-weight-light">SEARCH</h6>
        </div>

        <div className="col-6">
          <input
            type="text"
            className="form-control"
            value={searchText}
            onChange={(e) => setSearchText(e.target.value)}
          />
        </div>
      </div>
      {message && <div className="py-2">{message}</div>}
      <div className="row">
        {!message && result?.length <= 0 && (
          <div className="col-lg-6 py-2">
            No result matching the search criteria.
          </div>
        )}
        {result?.map((song) => (
          <div className="col-lg-6">
            <div
              className="border rounded py-2 px-4 my-2"
              style={{ background: "#fcfcfc" }}
            >
              <div className="row">
                <div className="col">
                  <div>
                    {song.name} ({song.albumName})
                  </div>
                  <div className="small">
                    {song.genre}, {song.year}
                  </div>
                  <div className="small">{song.artistName}</div>
                </div>
                <div className="col-auto d-flex justify-content-end align-items-center">
                  <i className="btn btn-dark btn-circle fas fa-play mr-2"></i>
                  <i className="btn btn-success btn-circle fas fa-plus"></i>
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
