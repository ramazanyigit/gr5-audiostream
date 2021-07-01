# Audiostream Microservices Project


### Example Kafka Configuration
```yaml
spring:
  kafka:
    consumer:
      bootstrap-servers: ${KAFKA_URI:localhost:9092}
      group-id: streaming-group
      auto-offset-reset: earliest
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
      properties:
        spring.json.trusted.packages: *
    producer:
      bootstrap-servers: ${KAFKA_URI:localhost:9092}
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer
```

### Example Kafka Producer and Consumer
Producer:
```java
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class KafkaProducer {
    private static final String TOPIC = "streaming";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(Object message) {
        log.debug(String.format("#KAFKA TOPIC %s: %s", TOPIC, message));
        kafkaTemplate.send(TOPIC, message);
    }
}
```

Consumer:
```java
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {
    // Replace the "Object" with the topic DTO
    @KafkaListener(topics = "collection")
    public void consume(Object message) {
        log.info(String.format("# Consumed: %s", message));
    }
}
```