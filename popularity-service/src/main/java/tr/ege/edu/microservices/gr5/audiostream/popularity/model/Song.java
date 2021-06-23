package tr.ege.edu.microservices.gr5.audiostream.popularity.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Table(name="t_song")
@Entity
@Data
public class Song {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name="UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name="artist")
    private UUID artistId;

    @Column(name="album")
    private UUID albumId;

    @Column(name="genre")
    private UUID genre;



}
