package tr.ege.edu.microservices.gr5.audiostream.streaming.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tr.ege.edu.microservices.gr5.audiostream.streaming.exception.StreamingException;
import tr.ege.edu.microservices.gr5.audiostream.streaming.feign.SongDetail;
import tr.ege.edu.microservices.gr5.audiostream.streaming.model.StreamCreationDTO;
import tr.ege.edu.microservices.gr5.audiostream.streaming.model.StreamLog;
import tr.ege.edu.microservices.gr5.audiostream.streaming.model.StreamStopDTO;
import tr.ege.edu.microservices.gr5.audiostream.streaming.service.StreamLogService;
import tr.ege.edu.microservices.gr5.audiostream.streaming.util.AuthenticationUtil;

import java.util.List;

@AllArgsConstructor
@RestController
@PreAuthorize("isAuthenticated()")
public class StreamingController {
    private final StreamLogService service;

    @GetMapping("/last-played")
    public List<SongDetail> lastRecords() {
        return service.getLast5Logs(AuthenticationUtil.getId());
    }

    @PostMapping("/play")
    public StreamLog play(@RequestBody StreamCreationDTO creationDTO) {
        return service.start(AuthenticationUtil.getId(), creationDTO.getSongId(),
                creationDTO.getPlayOffset());
    }

    @PostMapping("/stop")
    public StreamLog stop(@RequestBody StreamStopDTO stopDTO) throws StreamingException {
        return service.stop(stopDTO.getId(), stopDTO.getStopOffset());
    }
}
