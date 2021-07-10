package tr.ege.edu.microservices.gr5.audiostream.recommendation.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tr.ege.edu.microservices.gr5.audiostream.recommendation.model.GlobalChart;

@FeignClient("popularity-api")
public interface PopularityFeignProxy {
    @GetMapping("/genre/{genre}")
    GlobalChart getGenreTopTen(@PathVariable String genre);
}
