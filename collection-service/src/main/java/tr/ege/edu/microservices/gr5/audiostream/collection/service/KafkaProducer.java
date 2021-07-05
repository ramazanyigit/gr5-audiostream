package tr.ege.edu.microservices.gr5.audiostream.collection.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@AllArgsConstructor
public class KafkaProducer {
    private static final String TOPIC = "collection";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(Object message) {
        log.debug(String.format("#KAFKA TOPIC %s: %s", TOPIC, message));
        kafkaTemplate.send(TOPIC, message);
    }
}
