package tr.ege.edu.microservices.gr5.audiostream.streaming.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient("collection-api")
public interface CollectionFeignProxy {
    @GetMapping("/song/{id}")
    SongDetail getSong(@PathVariable UUID id);
}
