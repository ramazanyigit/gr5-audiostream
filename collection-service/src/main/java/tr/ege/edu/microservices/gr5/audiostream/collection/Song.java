package tr.ege.edu.microservices.gr5.audiostream.collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Song {
    //might be its own classes
    private String artist;
    private String albumName;
    private int releaseYear;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    public Song(String artist, String albumName, int releaseYear) {
        this.artist = artist;
        this.albumName = albumName;
        this.releaseYear = releaseYear;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbumName() {
        return albumName;
    }

    public int getReleaseYear() {
        return releaseYear;
    }
}
