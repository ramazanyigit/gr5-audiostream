package tr.ege.edu.microservices.gr5.audiostream.aaservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {
    @GetMapping("/")
    public String test() {
        return "hello";
    }
}
