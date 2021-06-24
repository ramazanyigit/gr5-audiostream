package tr.ege.edu.microservices.gr5.audiostream.collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Song {
    private String name;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;
    private UUID albumId;

    public Song(String name, UUID album) {
        this.name = name;
        this.albumId = album;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public UUID getAlbumId() {
        return albumId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlbumId(UUID albumId) {
        this.albumId = albumId;
    }


}
