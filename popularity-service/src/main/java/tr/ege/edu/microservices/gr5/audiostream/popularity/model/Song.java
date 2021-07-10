package tr.ege.edu.microservices.gr5.audiostream.popularity.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import tr.ege.edu.microservices.gr5.audiostream.popularity.type.Genre;

import javax.persistence.*;
import java.util.UUID;

@Table(name="t_song")
@Entity
@Data
public class Song {
    @Id
    private UUID id;


    @Column(name="genre")
    private Genre genre;
}
