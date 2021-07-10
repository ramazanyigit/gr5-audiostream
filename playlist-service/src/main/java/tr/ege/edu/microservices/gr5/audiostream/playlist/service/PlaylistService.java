package tr.ege.edu.microservices.gr5.audiostream.playlist.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tr.ege.edu.microservices.gr5.audiostream.playlist.CollectionFeignProxy;
import tr.ege.edu.microservices.gr5.audiostream.playlist.exception.PlaylistException;
import tr.ege.edu.microservices.gr5.audiostream.playlist.model.*;
import tr.ege.edu.microservices.gr5.audiostream.playlist.repository.PlaylistRepository;
import tr.ege.edu.microservices.gr5.audiostream.playlist.repository.SongRepository;
import tr.ege.edu.microservices.gr5.audiostream.playlist.util.AuthenticationUtil;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PlaylistService {
    private final SongRepository songRepository;
    private final PlaylistRepository playlistRepository;
    private final CollectionFeignProxy collectionFeignProxy;

    @Transactional
    public void addSong(UUID playlistId, UUID songID) throws PlaylistException { //Update
        Optional<Playlist> playlistOptional = getById(playlistId);

        if (playlistOptional.isEmpty()) {
            throw new PlaylistException("There is no playlist.");
        }

        Playlist playlist = playlistOptional.get();

        var newSong = new Song();
        newSong.setSongId(songID);

        playlist.getSongs().add(newSong);
        playlistRepository.save(playlist);
    }

    public boolean deletePlaylist(UUID id) { //Delete
        if (getById(id).isEmpty()) {
            return false;
        }

        playlistRepository.deleteById(id);
        return true;
    }

    public UUID deleteSong(UUID id) {
        songRepository.deleteById(id);
        return id;
    }

    public List<Playlist> getAllByUserId(UUID userId) {
        return playlistRepository.getAllByUserId(userId);
    }

    public Optional<Playlist> getByIdAndUserId(UUID id, UUID userId) {
        return playlistRepository.findByIdAndUserId(id, userId);
    }

    public Optional<Playlist> getById(UUID id) { //Read
        return playlistRepository.findById(id);
    }

    public Optional<PlaylistDTO> getPlaylistWithDetailsById(UUID id) {
        var playlistOptional = getById(id);

        if (playlistOptional.isEmpty()) {
            return Optional.empty();
        }

        var playlist = playlistOptional.get();
        var songList = new ArrayList<PlaylistSongDTO>();
        var playlistDTO = new PlaylistDTO(playlist.getId(), playlist.getName(), songList);

        for (Song song : playlist.getSongs()) {
            try {
                var songDetail = collectionFeignProxy.getSong(song.getSongId());
                songList.add(new PlaylistSongDTO(song.getId(), song.getSongId(), song.getDate(), songDetail));
            } catch(Exception ignored) {
                System.err.println(ignored.getMessage());
                songList.add(new PlaylistSongDTO(song.getId(), song.getSongId(), song.getDate(), null));
            }
        }

        return Optional.of(playlistDTO);
    }

    public Playlist save(PlaylistSaveRequest saveRequest) {
        var playlist = new Playlist();
        playlist.setName(saveRequest.name());
        playlist.setSongs(List.of());
        playlist.setUserId(AuthenticationUtil.getId());

        return playlistRepository.save(playlist);
    }

    public List<UUID> deleteByIds(List<UUID> ids) {
        List<UUID> deletedPlaylists = new ArrayList<>();

        for (UUID id : ids) {
            if (deletePlaylist(id)) {
                deletedPlaylists.add(id);
            }
        }

        return deletedPlaylists;
    }
}
