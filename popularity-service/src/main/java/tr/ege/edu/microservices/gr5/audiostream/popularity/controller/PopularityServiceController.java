package tr.ege.edu.microservices.gr5.audiostream.popularity.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.ege.edu.microservices.gr5.audiostream.popularity.dto.GlobalChartDTO;
import tr.ege.edu.microservices.gr5.audiostream.popularity.model.GlobalChart;
import tr.ege.edu.microservices.gr5.audiostream.popularity.service.PopularityService;
import tr.ege.edu.microservices.gr5.audiostream.popularity.type.Genre;

@RestController
@AllArgsConstructor
@PreAuthorize("isAuthenticated()")
public class PopularityServiceController {
    private final PopularityService popularityService;


    @GetMapping("/top-10")
    public ResponseEntity<GlobalChartDTO> getTopTen(){

            return ResponseEntity.ok(popularityService.getGlobalTopTenList());
    }
    @GetMapping("/top-100")
    public ResponseEntity<GlobalChartDTO> getTopHundred(){

        return ResponseEntity.ok(popularityService.getGlobalTopHundredList());
    }
    @GetMapping("/genre")
    public ResponseEntity<GlobalChartDTO> getGenreTop(Genre genre){
        return ResponseEntity.ok(popularityService.getGenreTop(genre));
    }

}
