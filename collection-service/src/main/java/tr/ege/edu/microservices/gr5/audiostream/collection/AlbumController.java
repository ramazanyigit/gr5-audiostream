package tr.ege.edu.microservices.gr5.audiostream.collection;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class AlbumController {

    @GetMapping("/album/{albumID}")
    private void getSongsOfAlbum(@PathVariable("albumID") UUID albumId) {
        //album idsine sahip olanlari dondur
    }

    @PostMapping("/album/search")
    private void searchAlbums(@RequestBody searchQuery query)
    {
        //query.getQuery();
        //isimle arama yap
    }

}
