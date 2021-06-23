package tr.ege.edu.microservices.gr5.audiostream.popularity.repository;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tr.ege.edu.microservices.gr5.audiostream.popularity.model.GlobalChart;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GlobalChartRepository extends JpaRepository<GlobalChart, UUID> {
    @Override
    List<GlobalChart> findAll();

    @Query(value = "FROM GlobalChart order by repeatCount desc limit 10",nativeQuery = true)
    List<GlobalChart> getGlobalTopTen();

    @Query(value = "FROM GlobalChart order by repeatCount desc limit 100",nativeQuery = true)
    List<GlobalChart> getGlobalTopHundred();

    GlobalChart findBySongId(UUID songId);

    @Query(value = "FROM GlobalChart G INNER JOIN Song S ON (G.song.id=S.id and S.genre=?1) " +
            "order by G.repeatCount limit 10",nativeQuery = true)
    List<GlobalChart> getGenreTopTen(@Param("genreId") UUID genreId);
}
