package tr.ege.edu.microservices.gr5.audiostream.collection.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tr.ege.edu.microservices.gr5.audiostream.collection.model.Album;
import tr.ege.edu.microservices.gr5.audiostream.collection.service.AlbumService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/album")
@AllArgsConstructor
public class AlbumController {
    private final AlbumService albumService;

    @GetMapping
    public List<Album> getAll(@RequestParam Optional<UUID> artistId) {
        if (artistId.isPresent()) {
            return albumService.getAllByArtistId(artistId);
        }

        return albumService.getAll();
    }

    @GetMapping("/{albumId}")
    public Album getAlbumById(@PathVariable UUID albumId) {
        return albumService.getById(albumId).orElseThrow();
    }

    @PostMapping
    public Album save(@RequestBody Album album) {
        return albumService.save(album);
    }

    @DeleteMapping
    public List<UUID> deleteEntities(@RequestBody List<UUID> ids) {
        var deletedIds = new ArrayList<UUID>();

        for(UUID id : ids) {
            if (albumService.deleteById(id)) {
                deletedIds.add(id);
            }
        }

        return deletedIds;
    }
}
