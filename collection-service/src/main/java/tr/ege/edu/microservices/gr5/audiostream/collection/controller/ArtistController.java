package tr.ege.edu.microservices.gr5.audiostream.collection.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tr.ege.edu.microservices.gr5.audiostream.collection.model.Artist;
import tr.ege.edu.microservices.gr5.audiostream.collection.service.ArtistService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/artist")
@AllArgsConstructor
public class ArtistController {
    private final ArtistService artistService;

    @GetMapping
    public List<Artist> getAll(@RequestParam Optional<String> name) {
        if (name.isEmpty() || name.get().isBlank()) {
            return artistService.getAll();
        }

        return artistService.getAllByName(name.get());
    }

    @GetMapping("/{artistId}")
    public Artist getArtistById(@PathVariable UUID artistId) {
        return artistService.getById(artistId).orElseThrow();
    }

    @PostMapping
    public Artist save(@RequestBody Artist artist) {
        return artistService.save(artist);
    }

    @DeleteMapping
    public List<UUID> deleteEntities(@RequestBody List<UUID> ids) {
        var deletedIds = new ArrayList<UUID>();

        for (UUID id : ids) {
            if (artistService.deleteById(id)) {
                deletedIds.add(id);
            }
        }

        return deletedIds;
    }
}
