package tr.ege.edu.microservices.gr5.audiostream.popularity.dto;

import tr.ege.edu.microservices.gr5.audiostream.popularity.type.Genre;

import java.util.UUID;

public class SongDTO {
    private UUID id;
    private UUID artistId;
    private UUID albumId;
    private Genre genre;

}
