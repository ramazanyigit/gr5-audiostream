package tr.ege.edu.microservices.gr5.audiostream.playlist.model;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Table(name="ps_playlist")
@Entity
@Data
public class Playlist {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name="UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name="name")
    private String name;

    @OneToMany
    @JoinColumn
    private Set<Song> songSet;

}
