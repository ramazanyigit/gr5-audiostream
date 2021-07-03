package tr.ege.edu.microservices.gr5.audiostream.collection.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID uuid;
    private String name;

    public Artist(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
