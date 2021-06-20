package tr.ege.edu.microservices.gr5.audiostream.playlist;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("collection-api")
public interface CollectionFeignProxy {
    @GetMapping("/test")
    String testAPI();
}
