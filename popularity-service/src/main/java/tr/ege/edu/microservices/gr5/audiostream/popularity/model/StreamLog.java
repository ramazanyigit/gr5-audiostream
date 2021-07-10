package tr.ege.edu.microservices.gr5.audiostream.popularity.model;

import java.time.OffsetDateTime;
import java.util.UUID;

public record StreamLog(UUID id, UUID userId, UUID songId, OffsetDateTime creationTimestamp, OffsetDateTime endTimestamp, Float playOffset, Float stopOffset) {
}
