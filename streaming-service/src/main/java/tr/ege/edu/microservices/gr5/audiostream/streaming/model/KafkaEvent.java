package tr.ege.edu.microservices.gr5.audiostream.streaming.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor(staticName = "of")
public class KafkaEvent {
    @NotNull
    private String type;

    @NotNull
    private Object payload;
}
