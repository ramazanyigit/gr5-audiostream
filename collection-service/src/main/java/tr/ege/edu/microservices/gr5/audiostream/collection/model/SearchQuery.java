package tr.ege.edu.microservices.gr5.audiostream.collection.model;

public class SearchQuery {
    private String query;
    public SearchQuery(String query) {
        this.query=query;
    }

    public String getQuery() {
        return query;
    }
}
