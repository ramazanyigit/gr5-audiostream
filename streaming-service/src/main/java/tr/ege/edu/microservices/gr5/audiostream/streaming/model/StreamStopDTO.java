package tr.ege.edu.microservices.gr5.audiostream.streaming.model;

import lombok.Data;

import java.util.UUID;

@Data
public class StreamStopDTO {
    private UUID id;
    private Float stopOffset;
}
