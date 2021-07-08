package tr.ege.edu.microservices.gr5.audiostream.playlist.model;

import java.util.List;
import java.util.UUID;

public record PlaylistDTO (UUID id, String name, List<PlaylistSongDTO> songs) {
}
