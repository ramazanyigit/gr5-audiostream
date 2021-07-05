package tr.ege.edu.microservices.gr5.audiostream.collection.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tr.ege.edu.microservices.gr5.audiostream.collection.model.Song;
import tr.ege.edu.microservices.gr5.audiostream.collection.model.SongDTO;
import tr.ege.edu.microservices.gr5.audiostream.collection.repository.SongRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SongService {
    private final SongRepository repository;

    public List<SongDTO> getAll() {
        return repository.getAll();
    }

    public List<SongDTO> getAllByAlbumId(UUID albumId) {
        return repository.getAllByAlbumId(albumId);
    }

    public Optional<Song> getById(UUID id) {
        return repository.findById(id);
    }

    public Song save(Song entity) {
        return repository.save(entity);
    }

    public boolean deleteById(UUID id) {
        if (getById(id).isEmpty()) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }

    public List<SongDTO> getAllByName(String name) {
        return repository.getAllByNameContainsIgnoreCase(name);
    }
}
