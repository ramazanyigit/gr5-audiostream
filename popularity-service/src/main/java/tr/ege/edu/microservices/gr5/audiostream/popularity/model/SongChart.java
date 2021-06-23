package tr.ege.edu.microservices.gr5.audiostream.popularity.model;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Table(name="t_song_chart")
@Entity
@Data
public class SongChart {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name="UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn
    private Song song;

    @Column(name="glb_repeat_count")
    private Integer glbRepeatCount;

}
