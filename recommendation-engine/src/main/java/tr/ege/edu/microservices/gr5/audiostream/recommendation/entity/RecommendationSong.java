package tr.ege.edu.microservices.gr5.audiostream.recommendation.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class RecommendationSong {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
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
