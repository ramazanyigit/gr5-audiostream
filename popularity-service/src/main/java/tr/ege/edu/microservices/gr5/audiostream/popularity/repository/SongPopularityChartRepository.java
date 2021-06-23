package tr.ege.edu.microservices.gr5.audiostream.popularity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.ege.edu.microservices.gr5.audiostream.popularity.model.SongPopularityChart;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SongPopularityChartRepository extends JpaRepository<SongPopularityChart, UUID> {
    @Override
    Optional<SongPopularityChart> findById(UUID uuid);
}
