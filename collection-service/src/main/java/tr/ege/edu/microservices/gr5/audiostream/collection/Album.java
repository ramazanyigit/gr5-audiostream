package tr.ege.edu.microservices.gr5.audiostream.collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Album {
    private String name;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    private UUID artistId;
    private int year;
    private Genre genre;

    public Album(String name, UUID artistId, int year, Genre genre) {
        this.name = name;
        this.artistId = artistId;
        this.year = year;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public UUID getArtistId() {
        return artistId;
    }

    public int getYear() {
        return year;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtistId(UUID artistId) {
        this.artistId = artistId;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
