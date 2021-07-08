package tr.ege.edu.microservices.gr5.audiostream.playlist.model;

import java.time.OffsetDateTime;
import java.util.UUID;

public record PlaylistSongDTO (UUID id, UUID songId, OffsetDateTime date, SongDetail detail) {
}
