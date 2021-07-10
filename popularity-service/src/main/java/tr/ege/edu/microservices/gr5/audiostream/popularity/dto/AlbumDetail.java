package tr.ege.edu.microservices.gr5.audiostream.popularity.dto;

import java.util.UUID;

public record AlbumDetail(UUID id, String name, ArtistDetail artist, int year, String genre) {
}

