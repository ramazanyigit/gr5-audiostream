package tr.ege.edu.microservices.gr5.audiostream.collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class SongController
{

    //@Autowired
    //SongService songService;
    //su asamada yok

    @GetMapping("/artist/{artistID}")
    private void getSongsByArtist(@PathVariable("artistID") UUID artistId) {
        //artist idsine sahip olanlari dondur
    }

    @GetMapping("/song/{songID}")
    private void getSong(@PathVariable("songID") UUID songId)
    {
        //sarkiya ulas
    }

    @PostMapping("/search")
    private void saveBook(@RequestBody searchQuery query)
    {
        //query.getQuery();
        //isimle arama yap
    }

}