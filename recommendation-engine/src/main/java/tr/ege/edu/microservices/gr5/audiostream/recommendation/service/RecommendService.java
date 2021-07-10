package tr.ege.edu.microservices.gr5.audiostream.recommendation.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tr.ege.edu.microservices.gr5.audiostream.recommendation.feign.CollectionFeignProxy;
import tr.ege.edu.microservices.gr5.audiostream.recommendation.feign.PopularityFeignProxy;
import tr.ege.edu.microservices.gr5.audiostream.recommendation.model.SongDetail;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
@AllArgsConstructor
@Slf4j
public class RecommendService {
    private final CollectionFeignProxy collectionFeignProxy;
    private final PopularityFeignProxy popularityFeignProxy;

    public List<SongDetail> getRecommendationListById(UUID songId) {
        try {
            SongDetail songDetail = collectionFeignProxy.getSong(songId);
            var genre = songDetail.album().genre();
            return popularityFeignProxy.getGenreTopTen(genre).songs();
        } catch (Exception e) {
            log.error(e.getMessage());
            return List.of();
        }
    }


    public SongDetail getRecommendationSongById(UUID songId) {
        try {
            SongDetail songDetail = collectionFeignProxy.getSong(songId);
            var genre = songDetail.album().genre();
            var songs = popularityFeignProxy.getGenreTopTen(genre).songs();
            return songs.get(ThreadLocalRandom.current().nextInt(songs.size()));
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
