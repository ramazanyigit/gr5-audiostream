package tr.ege.edu.microservices.gr5.audiostream.popularity.dto;

import java.util.UUID;

public record SongDetail(UUID id, String name, AlbumDetail album, Float duration) {
}
