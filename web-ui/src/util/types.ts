export interface Song {
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

export interface Playlist {
  id: string;
  name: string;
  userId: string;
  songs: {
    id: string;
    songId: string;
    date: string;
    detail: Song;
  }[];
}

export interface Chart {
  name: string;
  songs: Song[];
}
