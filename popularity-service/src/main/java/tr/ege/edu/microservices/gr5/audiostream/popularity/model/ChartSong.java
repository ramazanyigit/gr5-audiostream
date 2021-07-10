package tr.ege.edu.microservices.gr5.audiostream.popularity.model;

import tr.ege.edu.microservices.gr5.audiostream.popularity.type.Genre;

import java.util.UUID;

public interface ChartSong {
    UUID getSongId();
    Long getRepeatCount();
    Genre getGenre();
}
