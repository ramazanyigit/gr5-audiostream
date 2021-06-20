package tr.ege.edu.microservices.gr5.audiostream.collection;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class KafkaConsumer {
    @KafkaListener(topics = "collection")
    public void consume(String message) throws IOException {
        log.info(String.format("# Consumed: %s", message));
    }
}
