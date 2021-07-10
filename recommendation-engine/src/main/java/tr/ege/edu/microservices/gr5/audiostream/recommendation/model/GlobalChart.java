package tr.ege.edu.microservices.gr5.audiostream.recommendation.model;

import java.util.List;

public record GlobalChart(String name, List<SongDetail> songs) {
}
