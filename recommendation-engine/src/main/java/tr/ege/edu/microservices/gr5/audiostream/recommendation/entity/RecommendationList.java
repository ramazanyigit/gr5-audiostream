package tr.ege.edu.microservices.gr5.audiostream.recommendation.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.UUID;

public class RecommendationList {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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
