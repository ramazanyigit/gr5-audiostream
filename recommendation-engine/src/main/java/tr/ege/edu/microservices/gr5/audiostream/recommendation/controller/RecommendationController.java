package tr.ege.edu.microservices.gr5.audiostream.recommendation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RecommendationController {

    @GetMapping("/recommend/list/{songID}")
    private List<UUID> getRecommendationListBySongId(@PathVariable("songID") UUID songId) {
        // return a list consisting recommendation similar to the sent in request
        return new ArrayList<UUID>();
    }

    @GetMapping("/recommend/song/{songID}")
    private UUID getRecommendationSongBySongId(@PathVariable("songID") UUID songId) {
        // return a song similar to the sent in request
        return new UUID(0, 0);
    }



}
