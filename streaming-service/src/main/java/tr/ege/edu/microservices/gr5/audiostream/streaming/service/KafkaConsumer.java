package tr.ege.edu.microservices.gr5.audiostream.streaming.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class KafkaConsumer {
    @KafkaListener(topics = "collection")
    public void consume(String message) {
        log.info(String.format("# Consumed: %s", message));
    }
}