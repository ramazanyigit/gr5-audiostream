package tr.ege.edu.microservices.gr5.audiostream.collection.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tr.ege.edu.microservices.gr5.audiostream.collection.model.Song;
import tr.ege.edu.microservices.gr5.audiostream.collection.model.SongDTO;
import tr.ege.edu.microservices.gr5.audiostream.collection.service.SongService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/song")
@AllArgsConstructor
public class SongController {
    private final SongService service;

    @GetMapping
    public List<SongDTO> getAll(@RequestParam(required = false) Optional<String> name,
                                @RequestParam Optional<UUID> albumId) {
        if (albumId.isPresent()) {
            return service.getAllByAlbumId(albumId.get());
        }

        if (name.isEmpty() || name.get().isBlank()) {
            return service.getAll();
        }

        return service.getAllByName(name.get());
    }

    @GetMapping("/{songId}")
    public Song getSongById(@PathVariable UUID songId) {
        return service.getById(songId).orElseThrow();
    }

    @PostMapping
    public Song saveSong(@RequestBody Song song) {
        return service.save(song);
    }

    @DeleteMapping
    public List<UUID> deleteSongs(@RequestBody List<UUID> songIds) {
        var deletedIds = new ArrayList<UUID>();

        for (UUID id : songIds) {
            if (service.deleteById(id)) {
                deletedIds.add(id);
            }
        }

        return deletedIds;
    }
}