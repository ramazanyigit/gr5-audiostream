package tr.ege.edu.microservices.gr5.audiostream.popularity.model;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Table(name="t_song_popular_chart")
@Entity
@Data
public class SongPopularityChart {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name="UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name="report_date")
    private UUID reportDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Song song;

    @Column(name = "popularity_ratio")
    private Double popularityRatio;
}
