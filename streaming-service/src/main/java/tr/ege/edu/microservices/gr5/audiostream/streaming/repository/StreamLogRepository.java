package tr.ege.edu.microservices.gr5.audiostream.streaming.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.ege.edu.microservices.gr5.audiostream.streaming.model.StreamLog;

import java.util.UUID;

@Repository
public interface StreamLogRepository extends JpaRepository<StreamLog, UUID> {

}
