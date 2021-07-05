package tr.ege.edu.microservices.gr5.audiostream.playlist.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
public class Song {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name="UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(updatable = false, nullable = false)
    private UUID songId;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false, updatable = false)
    private OffsetDateTime date;

    @PrePersist
    public void prePersist() {
        date = OffsetDateTime.now();
    }
}
