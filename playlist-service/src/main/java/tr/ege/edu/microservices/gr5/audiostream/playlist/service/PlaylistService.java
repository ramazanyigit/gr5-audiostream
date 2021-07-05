package tr.ege.edu.microservices.gr5.audiostream.playlist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.ege.edu.microservices.gr5.audiostream.playlist.model.Playlist;
import tr.ege.edu.microservices.gr5.audiostream.playlist.repository.PlaylistRepository;
import java.util.UUID;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository){
        this.playlistRepository = playlistRepository;

    }
    public void createPlaylist(){ //Create

    }

    public void addSong(UUID playlistId,UUID songID){ //Update

    }

    public void deleteSong(UUID id){ //Update

    }

    public void deletePlaylist(UUID playlistId){ //Delete
        playlistRepository.deleteById(playlistId);
    }
    public Playlist getPlaylist(UUID playlistId){ //Read
        return playlistRepository.getByPlaylistId(playlistId).get();
    }

}
