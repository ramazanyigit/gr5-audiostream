package tr.ege.edu.microservices.gr5.audiostream.collection.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tr.ege.edu.microservices.gr5.audiostream.collection.model.Album;
import tr.ege.edu.microservices.gr5.audiostream.collection.repository.AlbumRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AlbumService {
    private final AlbumRepository repository;

    public List<Album> getAll() {
        return repository.findAll();
    }

    public List<Album> getAllByArtistId(UUID artistId) {
        return repository.findAllByArtistId(artistId);
    }

    public Optional<Album> getById(UUID id) {
        return repository.findById(id);
    }

    public Album save(Album entity) {
        return repository.save(entity);
    }

    public boolean deleteById(UUID id) {
        if (getById(id).isEmpty()) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}
