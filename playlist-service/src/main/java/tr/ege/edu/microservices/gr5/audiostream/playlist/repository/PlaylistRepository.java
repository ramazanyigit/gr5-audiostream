package tr.ege.edu.microservices.gr5.audiostream.playlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.ege.edu.microservices.gr5.audiostream.playlist.model.Playlist;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, UUID> {
    List<Playlist> getAllByUserId(UUID userId);

    Optional<Playlist> findByIdAndUserId(UUID id, UUID userId);
}
