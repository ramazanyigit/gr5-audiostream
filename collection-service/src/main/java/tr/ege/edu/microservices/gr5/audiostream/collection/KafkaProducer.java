package tr.ege.edu.microservices.gr5.audiostream.collection;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class KafkaProducer {
    private static final String TOPIC = "collection";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String message) {
        log.info(String.format("# %s // Message: %s", TOPIC, message));
        kafkaTemplate.send(TOPIC, message);
    }

//    @Scheduled(fixedRate = 10000)
//    public void test() {
//        send("test collection message");
//    }
}
