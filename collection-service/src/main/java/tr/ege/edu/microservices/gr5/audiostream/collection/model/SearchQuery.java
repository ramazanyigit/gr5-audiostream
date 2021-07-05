package tr.ege.edu.microservices.gr5.audiostream.collection.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchQuery {
    private String query;
}
