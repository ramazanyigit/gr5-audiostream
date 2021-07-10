package tr.ege.edu.microservices.gr5.audiostream.popularity.model;

import javax.validation.constraints.NotNull;

public record KafkaEvent(@NotNull String type, @NotNull StreamLog payload) {
}
