package tr.ege.edu.microservices.gr5.audiostream.collection;

import org.springframework.web.bind.annotation.*;
import tr.ege.edu.microservices.gr5.audiostream.collection.model.SearchQuery;

import java.util.UUID;

@RestController
public class SongController
{

    //@Autowired
    //SongService songService;
    //su asamada yok

    @GetMapping("/song/{songID}")
    private void getSong(@PathVariable("songID") UUID songId)
    {
        //sarkiya ulas
    }

    @PostMapping("/search")
    private void searchSongs(@RequestBody SearchQuery query)
    {
        //query.getQuery();
        //isimle arama yap
    }

}