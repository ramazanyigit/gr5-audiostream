package tr.ege.edu.microservices.gr5.audiostream.recommendation.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tr.ege.edu.microservices.gr5.audiostream.recommendation.CollectionFeignProxy;
import tr.ege.edu.microservices.gr5.audiostream.recommendation.model.SongDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RecommendationService {

    private final CollectionFeignProxy collectionFeignProxy;


    public List<SongDetail> getRecommendationListById(UUID songId) {
        try {
            SongDetail songDetail = collectionFeignProxy.getSong(songId);

        } catch (Exception exc) {
            System.out.println(exc);
        }

        return new ArrayList<>();
    }


    public SongDetail getRecommendationSongById(UUID songId) {
        try {
            SongDetail songDetail = collectionFeignProxy.getSong(songId);

        } catch (Exception exc) {
            System.out.println(exc);
        }

        return new SongDetail("234", "dfd", "dsfsdf", 23.3);
    }

}
