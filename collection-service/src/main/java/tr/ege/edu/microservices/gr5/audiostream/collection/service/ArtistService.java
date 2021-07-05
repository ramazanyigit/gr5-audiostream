package tr.ege.edu.microservices.gr5.audiostream.collection.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tr.ege.edu.microservices.gr5.audiostream.collection.model.Album;
import tr.ege.edu.microservices.gr5.audiostream.collection.model.Artist;
import tr.ege.edu.microservices.gr5.audiostream.collection.repository.AlbumRepository;
import tr.ege.edu.microservices.gr5.audiostream.collection.repository.ArtistRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ArtistService {
    private final ArtistRepository repository;

    public List<Artist> getAll() {
        return repository.findAll();
    }

    public Optional<Artist> getById(UUID id) {
        return repository.findById(id);
    }

    public Artist save(Artist entity) {
        return repository.save(entity);
    }

    public boolean deleteById(UUID id) {
        if (getById(id).isEmpty()) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }

    public List<Artist> getAllByName(String name) {
        return repository.findAllByNameContainsIgnoreCase(name);
    }
}
