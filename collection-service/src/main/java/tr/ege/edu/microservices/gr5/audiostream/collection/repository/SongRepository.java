package tr.ege.edu.microservices.gr5.audiostream.collection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.ege.edu.microservices.gr5.audiostream.collection.model.Song;
import tr.ege.edu.microservices.gr5.audiostream.collection.model.SongDTO;

import java.util.List;
import java.util.UUID;

@Repository
public interface SongRepository extends JpaRepository<Song, UUID> {
    List<SongDTO> getAllByAlbumId(UUID albumId);

    List<SongDTO> getAll();

    List<SongDTO> getAllByNameContainsIgnoreCase(String name);
}
