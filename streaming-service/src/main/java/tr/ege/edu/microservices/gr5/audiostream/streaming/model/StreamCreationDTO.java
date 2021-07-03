package tr.ege.edu.microservices.gr5.audiostream.streaming.model;

import lombok.Data;

import java.util.UUID;

@Data
public class StreamCreationDTO {
    private UUID songId;
    private Float playOffset;
}
