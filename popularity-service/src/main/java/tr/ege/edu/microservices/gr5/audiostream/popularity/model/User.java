package tr.ege.edu.microservices.gr5.audiostream.popularity.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Table(name="t_user")
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name="UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name="country_code")
    private String countryCode;

    @OneToMany
    @JoinColumn
    private Set<UserSongChart> userSongChart;

}
