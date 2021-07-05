package tr.ege.edu.microservices.gr5.audiostream.playlist.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
public class Song {
    @Id
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false, updatable = false)
    private OffsetDateTime date;

    @PrePersist
    public void prePersist() {
        date = OffsetDateTime.now();
    }
}
