package tr.ege.edu.microservices.gr5.audiostream.streaming;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("isAuthenticated()")
public class TestController {
    @GetMapping("/ping")
    public String test() {
        var securityContext = SecurityContextHolder.getContext();
        return securityContext.getAuthentication().getName();
    }
}
