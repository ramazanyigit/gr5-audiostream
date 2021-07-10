package tr.ege.edu.microservices.gr5.audiostream.playlist.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tr.ege.edu.microservices.gr5.audiostream.playlist.exception.PlaylistException;
import tr.ege.edu.microservices.gr5.audiostream.playlist.model.Playlist;
import tr.ege.edu.microservices.gr5.audiostream.playlist.model.PlaylistDTO;
import tr.ege.edu.microservices.gr5.audiostream.playlist.model.PlaylistSaveRequest;
import tr.ege.edu.microservices.gr5.audiostream.playlist.model.SongAddRequest;
import tr.ege.edu.microservices.gr5.audiostream.playlist.service.PlaylistService;
import tr.ege.edu.microservices.gr5.audiostream.playlist.util.AuthenticationUtil;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@PreAuthorize("isAuthenticated()")
public class PlaylistController {
    private final PlaylistService service;


    @GetMapping
    public List<Playlist> getPlaylistsOfCurrentUser() {
        var userId = AuthenticationUtil.getId();

        return service.getAllByUserId(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaylistDTO> getPlaylistById(@PathVariable UUID id) {
        Optional<Playlist> playlistOptional = service.getByIdAndUserId(id, AuthenticationUtil.getId());

        if (playlistOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(service.getPlaylistWithDetailsById(id).get());
    }

    @DeleteMapping
    public List<UUID> deletePlaylists(@RequestBody List<UUID> ids) {
        return service.deleteByIds(ids);
    }

    @PostMapping
    public Playlist savePlaylist(@RequestBody PlaylistSaveRequest requestEntity) {
        return service.save(requestEntity);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToPlaylist(@RequestBody SongAddRequest requestEntity) throws PlaylistException {
        service.addSong(requestEntity.playlistId(), requestEntity.songId());
        return ResponseEntity.ok("");
    }

    @DeleteMapping("/song")
    public UUID deleteSongFromPlaylist(@RequestBody String id) {
        return service.deleteSong(UUID.fromString(id));
    }
 }
