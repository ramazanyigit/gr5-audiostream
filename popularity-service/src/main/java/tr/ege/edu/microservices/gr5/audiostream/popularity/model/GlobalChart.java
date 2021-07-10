package tr.ege.edu.microservices.gr5.audiostream.popularity.model;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import tr.ege.edu.microservices.gr5.audiostream.popularity.type.Genre;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.UUID;

@Table(name = "global_chart")
@Entity
@Data
public class GlobalChart {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime reportDate;

    @Column(nullable = false, updatable = false)
    private UUID songId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ColumnDefault("'OTHER'")
    private Genre genre;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long repeatCount;
}
