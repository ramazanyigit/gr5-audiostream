package tr.ege.edu.microservices.gr5.audiostream.collection.model;

import org.springframework.beans.factory.annotation.Value;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface SongDTO {
    UUID getId();

    @Value("#{target.album?.id}")
    UUID getAlbumId();

    @Value("#{target.album?.artist?.id}")
    UUID getArtistId();

    String getName();

    @Value("#{target.album?.name}")
    String getAlbumName();

    @Value("#{target.album?.artist?.name}")
    String getArtistName();

    @Value("#{target.album?.genre}")
    String getGenre();

    @Value("#{target.album?.year}")
    Integer getYear();

    OffsetDateTime getCreationTimestamp();

    Float getDuration();
}
