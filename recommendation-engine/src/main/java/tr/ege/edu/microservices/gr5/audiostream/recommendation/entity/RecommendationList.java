package tr.ege.edu.microservices.gr5.audiostream.recommendation.entity;

import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

public class RecommendationList {
    @Id
    private UUID recommendationId;
    private List<UUID> recommendedSongs;

    public RecommendationList() {
    }

    public RecommendationList(List<UUID> recommendedSongs) {
        this.recommendedSongs = recommendedSongs;
    }

    public List<UUID> getRecommendedSongs() {
        return recommendedSongs;
    }

    public void setRecommendedSongs(List<UUID> recommendedSongs) {
        this.recommendedSongs = recommendedSongs;
    }
}
