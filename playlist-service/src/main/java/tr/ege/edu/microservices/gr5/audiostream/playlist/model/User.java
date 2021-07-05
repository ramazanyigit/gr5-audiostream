package tr.ege.edu.microservices.gr5.audiostream.playlist.model;

import lombok.Data;
import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Table(name="ps_user")
@Entity
@Data
public class User {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @OneToMany
    @JoinColumn
    private Set<Playlist> playlistSet;

}