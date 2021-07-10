package tr.ege.edu.microservices.gr5.audiostream.streaming.model;

import tr.ege.edu.microservices.gr5.audiostream.streaming.feign.SongDetail;

import java.time.OffsetDateTime;
import java.util.UUID;

public record StreamLogDTO(UUID id, UUID songId, OffsetDateTime creationTimestamp, OffsetDateTime endTimestamp,
                           Float playOffset, Float stopOffset, SongDetail song) {
}
