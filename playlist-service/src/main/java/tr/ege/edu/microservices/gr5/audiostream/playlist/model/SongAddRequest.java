package tr.ege.edu.microservices.gr5.audiostream.playlist.model;

import java.util.UUID;

public record SongAddRequest(UUID playlistId, UUID songId) {
}

