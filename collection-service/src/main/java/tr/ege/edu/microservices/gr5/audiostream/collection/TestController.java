package tr.ege.edu.microservices.gr5.audiostream.collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public String feignTest() {
        return "feign test!";
    }
}
