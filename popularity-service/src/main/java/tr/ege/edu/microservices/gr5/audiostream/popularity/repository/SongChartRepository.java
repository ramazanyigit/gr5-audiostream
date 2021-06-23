package tr.ege.edu.microservices.gr5.audiostream.popularity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.ege.edu.microservices.gr5.audiostream.popularity.model.SongChart;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SongChartRepository extends JpaRepository<SongChart, UUID> {
    @Override
    Optional<SongChart> findById(UUID uuid);
}
