package tr.ege.edu.microservices.gr5.audiostream.collection;

public class searchQuery {
    private String query;
    public searchQuery(String query) {
        this.query=query;
    }

    public String getQuery() {
        return query;
    }
}
