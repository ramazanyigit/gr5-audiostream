package tr.ege.edu.microservices.gr5.audiostream.popularity.common;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestProxyController {
    private final CollectionFeignProxy feignProxy;

    @GetMapping("/proxy")
    public String feignTest() {
        return feignProxy.testAPI();
    }
}
