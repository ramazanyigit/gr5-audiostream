package tr.ege.edu.microservices.gr5.audiostream.playlist.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.ege.edu.microservices.gr5.audiostream.playlist.model.Playlist;
import tr.ege.edu.microservices.gr5.audiostream.playlist.repository.PlaylistRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PlaylistService {
    private final PlaylistRepository playlistRepository;

    public void createPlaylist(){ //Create

    }

    public void addSong(UUID playlistId, UUID songID){ //Update

    }

    public void deleteSong(UUID id){ //Update

    }

    public void deletePlaylist(UUID id){ //Delete

        playlistRepository.deleteById(id);
    }

    public Playlist getById(UUID id){ //Read
        return playlistRepository.findById(id).orElse(null);
    }

    public Optional<Playlist> findById(UUID id) {
        return playlistRepository.findById(id);
    }
}
