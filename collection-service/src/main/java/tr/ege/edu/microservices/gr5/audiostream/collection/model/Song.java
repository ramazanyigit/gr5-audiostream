package tr.ege.edu.microservices.gr5.audiostream.collection.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Song {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(nullable = false, updatable = false)
    private UUID id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "album_id", referencedColumnName = "id")
    private Album album;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime creationTimestamp;

    @PrePersist
    public void prePersist() {
        creationTimestamp = OffsetDateTime.now();
    }
}
