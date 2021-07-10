package tr.ege.edu.microservices.gr5.audiostream.popularity.repository;

import feign.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tr.ege.edu.microservices.gr5.audiostream.popularity.model.ChartSong;
import tr.ege.edu.microservices.gr5.audiostream.popularity.model.GlobalChart;
import tr.ege.edu.microservices.gr5.audiostream.popularity.type.Genre;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GlobalChartRepository extends JpaRepository<GlobalChart, UUID> {
    @Query("SELECT gc.songId as songId, SUM(gc.repeatCount) as repeatCount, MAX(gc.genre) as genre FROM GlobalChart gc group by gc.songId order by repeatCount desc")
    List<ChartSong> getGlobalTopNSongs(Pageable pageable);

    @Query("SELECT gc.songId as songId, SUM(gc.repeatCount) as repeatCount, MAX(gc.genre) as genre FROM GlobalChart gc where gc.genre = :genre group by gc.songId order by repeatCount desc")
    List<ChartSong> getGlobalTopNSongsByGenre(@Param("genre") Genre genre, Pageable pageable);

    Optional<GlobalChart> findBySongIdAndReportDate(UUID songId, OffsetDateTime date);
}
