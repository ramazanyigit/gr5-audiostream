package tr.ege.edu.microservices.gr5.audiostream.streaming.feign;

import java.util.UUID;

public record AlbumDetail(UUID id, String name, ArtistDetail artist, int year, String genre) {
}
