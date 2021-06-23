package tr.ege.edu.microservices.gr5.audiostream.popularity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserSongChart extends JpaRepository<UserSongChart, UUID> {
    @Override
    Optional<UserSongChart> findById(UUID uuid);
}
