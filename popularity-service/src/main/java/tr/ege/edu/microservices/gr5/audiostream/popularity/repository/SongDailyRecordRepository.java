package tr.ege.edu.microservices.gr5.audiostream.popularity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tr.ege.edu.microservices.gr5.audiostream.popularity.model.SongDailyRecord;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SongDailyRecordRepository extends JpaRepository<SongDailyRecord, UUID> {
    @Override
    Optional<SongDailyRecord> findById(UUID uuid);

    @Query("FROM SongDailyRecord where reportDate=current_date order by dailyRepeatCount desc")
    List<SongDailyRecord> getByReportDate(Date reportDate);
}
