import { store } from "@risingstack/react-easy-state";
import StreamingAPI from "../api/StreamingAPI";

export interface StreamLogWithSong {
  id: string;
  songId: string;
  creationTimestamp: string;
  endTimestamp: string;
  playOffset: number;
  stopOffset: number;
  song: {
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
}

let isRunning = false;

const streamingStore = store({
  currentPlaying: undefined as StreamLogWithSong | undefined,
  update() {
    if (isRunning) {
      return;
    }

    isRunning = true;
    StreamingAPI.getCurrentPlaying()
      .then(({ data }) => (streamingStore.currentPlaying = data))
      .catch(() => (streamingStore.currentPlaying = undefined))
      .finally(() => (isRunning = false));
  },
});

export default streamingStore;
