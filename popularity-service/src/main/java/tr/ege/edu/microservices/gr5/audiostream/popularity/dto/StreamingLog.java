package tr.ege.edu.microservices.gr5.audiostream.popularity.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record StreamingLog(UUID userId, UUID songId, OffsetDateTime creationTimestamp, Float playOffset) {
}
