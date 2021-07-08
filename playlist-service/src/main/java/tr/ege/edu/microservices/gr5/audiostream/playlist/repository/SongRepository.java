package tr.ege.edu.microservices.gr5.audiostream.playlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.ege.edu.microservices.gr5.audiostream.playlist.model.Song;

import java.util.UUID;

public interface SongRepository extends JpaRepository<Song, UUID> {
}
