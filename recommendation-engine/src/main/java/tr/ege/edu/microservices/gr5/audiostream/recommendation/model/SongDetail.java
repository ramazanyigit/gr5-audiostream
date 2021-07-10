package tr.ege.edu.microservices.gr5.audiostream.recommendation.model;

import java.util.UUID;

public record SongDetail(UUID id, String name, AlbumDetail album, Float duration) {
}
