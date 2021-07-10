package tr.ege.edu.microservices.gr5.audiostream.recommendation.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tr.ege.edu.microservices.gr5.audiostream.recommendation.CollectionFeignProxy;
import tr.ege.edu.microservices.gr5.audiostream.recommendation.PopularityFeignProxy;
import tr.ege.edu.microservices.gr5.audiostream.recommendation.dto.GlobalChartDTO;
import tr.ege.edu.microservices.gr5.audiostream.recommendation.model.SongDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RecommendationService {

    private final CollectionFeignProxy collectionFeignProxy;
    private final PopularityFeignProxy popularityFeignProxy;


    public List<SongDetail> getRecommendationListById(UUID songId) {

        List<SongDetail> topTenSongsList = new ArrayList<SongDetail>();

        try {
            SongDetail songDetail = collectionFeignProxy.getSong(songId);

            var genre = songDetail.album().genre();

            topTenSongsList =  popularityFeignProxy.getGenreTopTen(genre).songs;


        } catch (Exception exc) {
            System.err.println(exc.getMessage());
        }

        return topTenSongsList;
    }


    public SongDetail getRecommendationSongById(UUID songId) {

        List<SongDetail> topTenSongsList = new ArrayList<SongDetail>();

        try {
            SongDetail songDetail = collectionFeignProxy.getSong(songId);

            var genre = songDetail.album().genre();

            topTenSongsList =  popularityFeignProxy.getGenreTopTen(genre).songs;

        } catch (Exception exc) {
            System.err.println(exc.getMessage());
        }

        return topTenSongsList.get(new Random().nextInt());
    }

}
