package tr.ege.edu.microservices.gr5.audiostream.playlist.model;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public record PlaylistSaveRequest(UUID id, @NotBlank String name) {
}
