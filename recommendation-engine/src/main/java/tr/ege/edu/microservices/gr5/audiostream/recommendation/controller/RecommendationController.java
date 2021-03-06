package tr.ege.edu.microservices.gr5.audiostream.recommendation.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tr.ege.edu.microservices.gr5.audiostream.recommendation.model.SongDetail;
import tr.ege.edu.microservices.gr5.audiostream.recommendation.service.RecommendService;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@PreAuthorize("isAuthenticated()")
public class RecommendationController {
    private final RecommendService service;

    @GetMapping("/list/{songID}")
    public List<SongDetail> getListById(@PathVariable("songID") UUID songId) {
        return service.getRecommendationListById(songId);
    }

    @GetMapping("/song/{songID}")
    public SongDetail getSongById(@PathVariable("songID") UUID songId) {
        return service.getRecommendationSongById(songId);
    }
}
