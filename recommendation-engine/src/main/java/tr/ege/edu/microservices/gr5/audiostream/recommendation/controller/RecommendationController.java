package tr.ege.edu.microservices.gr5.audiostream.recommendation.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tr.ege.edu.microservices.gr5.audiostream.recommendation.model.SongDetail;
import tr.ege.edu.microservices.gr5.audiostream.recommendation.service.RecommendationService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@PreAuthorize("isAuthenticated()")
public class RecommendationController {
    public final RecommendationService recommendationService;


    @GetMapping("/recommend/list/{songID}")
    private List<SongDetail> getRecommendationListBySongId(@PathVariable("songID") UUID songId) {
        return recommendationService.getRecommendationListById(songId);
    }

    @GetMapping("/recommend/song/{songID}")
    private SongDetail getRecommendationSongBySongId(@PathVariable("songID") UUID songId) {
        return recommendationService.getRecommendationSongById(songId);
    }



}
