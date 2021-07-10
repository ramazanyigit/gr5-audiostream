package tr.ege.edu.microservices.gr5.audiostream.popularity.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tr.ege.edu.microservices.gr5.audiostream.popularity.model.GlobalChartDTO;
import tr.ege.edu.microservices.gr5.audiostream.popularity.service.PopularityService;
import tr.ege.edu.microservices.gr5.audiostream.popularity.type.Genre;

@RestController
@AllArgsConstructor
public class PopularityContoller {
    private final PopularityService popularityService;

    @GetMapping("/top-10")
    public ResponseEntity<GlobalChartDTO> getTopTen() {
        return ResponseEntity.ok(popularityService.getGlobalTopTenList());
    }

    @GetMapping("/top-100")
    public ResponseEntity<GlobalChartDTO> getTopHundred() {
        return ResponseEntity.ok(popularityService.getGlobalTopHundredList());
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<GlobalChartDTO> getGenreTop(@PathVariable String genre) {
        return ResponseEntity.ok(popularityService.getGenreTop(Genre.valueOf(genre)));
    }
}
