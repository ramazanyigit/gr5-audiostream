package tr.ege.edu.microservices.gr5.audiostream.streaming.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@Log4j2
public class KafkaConsumer {
    @KafkaListener(topics = "collection")
    public void consume(JsonNode message) {
        log.info(String.format("# Consumed: %s", message));
    }
}