package tr.ege.edu.microservices.gr5.audiostream.collection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tr.ege.edu.microservices.gr5.audiostream.collection.model.SearchQuery;

import java.util.UUID;

@Controller
public class ArtistController {

    @GetMapping("/artist/{artistID}")
    private void getSongsByArtist(@PathVariable("artistID") UUID artistId) {
        //artist idsine sahip olanlari dondur
    }

    @PostMapping("/artist/search")
    private void searchArtists(@RequestBody SearchQuery query)
    {
        //query.getQuery();
        //isimle arama yap
    }
}
