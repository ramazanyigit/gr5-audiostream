package tr.ege.edu.microservices.gr5.audiostream.popularity.model;


import java.util.List;

public record GlobalChartDTO(String name, List<SongDetail> songs) {
}
