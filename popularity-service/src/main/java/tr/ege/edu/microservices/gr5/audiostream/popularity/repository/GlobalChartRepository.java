package tr.ege.edu.microservices.gr5.audiostream.popularity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.ege.edu.microservices.gr5.audiostream.popularity.model.GlobalChart;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GlobalChartRepository extends JpaRepository<GlobalChart, UUID> {
    @Override
    List<GlobalChart> findAll();


    GlobalChart findBySongId(UUID songId);
}
