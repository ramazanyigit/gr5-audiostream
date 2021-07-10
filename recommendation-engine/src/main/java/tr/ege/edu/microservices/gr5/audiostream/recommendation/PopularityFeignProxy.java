package tr.ege.edu.microservices.gr5.audiostream.recommendation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tr.ege.edu.microservices.gr5.audiostream.recommendation.dto.GlobalChartDTO;
import tr.ege.edu.microservices.gr5.audiostream.recommendation.model.SongDetail;

import java.util.UUID;

@FeignClient("popularity-api")
public interface PopularityFeignProxy {
    @GetMapping("/genre")
    GlobalChartDTO getGenreTopTen(@PathVariable String genre);
}
