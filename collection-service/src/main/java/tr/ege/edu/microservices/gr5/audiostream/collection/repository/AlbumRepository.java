package tr.ege.edu.microservices.gr5.audiostream.collection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.ege.edu.microservices.gr5.audiostream.collection.model.Album;

import java.util.UUID;

@Repository
public interface AlbumRepository extends JpaRepository<Album, UUID> {
}
