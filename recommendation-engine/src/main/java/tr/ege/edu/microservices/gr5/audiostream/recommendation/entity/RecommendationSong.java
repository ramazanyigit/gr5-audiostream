package tr.ege.edu.microservices.gr5.audiostream.recommendation.entity;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public class RecommendationSong {

    @Id
    private UUID recommendationId;
    private UUID recommendedSong;

    public RecommendationSong(){}

    public RecommendationSong(UUID recommendedSong) {
        this.recommendedSong=recommendedSong;
    }


    public UUID getRecommendedSong() {
        return recommendedSong;
    }

    public void setRecommendedSong(UUID recommendedSong) {
        this.recommendedSong = recommendedSong;
    }
}
