# Audiostream Microservices Project

### Service API URI's
- collection-service: http://localhost:8080/api/collection/
- playlist-service: http://localhost:8080/api/playlist/
- popularity-service: http://localhost:8080/api/popularity/
- recommendation-engine: http://localhost:8080/api/recommendation/
- streaming-service: http://localhost:8080/api/streaming/

**Note:** When using front-end services the api gateway connected to http://localhost:3000/api with reverse proxy.

### Initial Credentials
- Keycloak Master Admin: http://localhost:8763

    admin/password


- Application: http://localhost:8080
    
    admin/admin

    user/user



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

### Example Feign Client
```java
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("service-name")
public interface CollectionFeignProxy {
    @GetMapping("/api-url")
    String testAPI();
}
```

Service Names for FeignClient:
- collection-api
- playlist-api
- popularity-api
- recommendation-engine
- streaming-api

### Kafka Consumer for Streaming Topic
Code Example:
```java
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class KafkaConsumer {
    @KafkaListener(topics = "streaming")
    public void consume(JsonNode message) {
        log.info(String.format("# Consumed: %s", message));
    }
}
```

Message Structure:
```json5
{
  "type": "START", // can be "START" or "STOP"
  "payload": {
    "id": "12ec751d-fabf-45c7-aa75-75cb806c8fb2",
    "userId": "a4ab0846-1529-40b0-b711-a6a70b7eb277",
    "songId": "062e4545-f5d1-437f-bead-c8679a459762",
    "creationTimestamp": "2019-08-31T23:49:05.629+08:00",
    "endTimestamp": "2019-08-31T23:51:05.629+08:00", // if event type is start, endTimestamp must be null
    "playOffset": 0.5,
    "endOffset": 223.52, // if event type is start, offset must be null
  }
}
```