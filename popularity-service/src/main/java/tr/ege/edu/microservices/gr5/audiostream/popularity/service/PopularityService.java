package tr.ege.edu.microservices.gr5.audiostream.popularity.service;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tr.ege.edu.microservices.gr5.audiostream.popularity.common.CollectionFeignProxy;
import tr.ege.edu.microservices.gr5.audiostream.popularity.model.ChartSong;
import tr.ege.edu.microservices.gr5.audiostream.popularity.model.GlobalChartDTO;
import tr.ege.edu.microservices.gr5.audiostream.popularity.model.SongDetail;
import tr.ege.edu.microservices.gr5.audiostream.popularity.repository.GlobalChartRepository;
import tr.ege.edu.microservices.gr5.audiostream.popularity.type.Genre;

import java.util.List;

@Service
@AllArgsConstructor
public class PopularityService {
    private final GlobalChartRepository globalChartRepository;
    private final CollectionFeignProxy collectionFeignProxy;

    public GlobalChartDTO getGlobalTopTenList() {
        return getGlobalTopNList(10);
    }

    public GlobalChartDTO getGlobalTopHundredList() {
        return getGlobalTopNList(100);
    }

    public GlobalChartDTO getGlobalTopNList(int n) {
        return createChart("Best " + n + " songs", globalChartRepository.getGlobalTopNSongs(PageRequest.of(0, n)));
    }

    public GlobalChartDTO getGenreTop(Genre genre) {
        return createChart("Best 10 " + genre.toString() + " songs", globalChartRepository.getGlobalTopNSongsByGenre(genre, PageRequest.of(0, 10)));
    }

    public GlobalChartDTO createChart(String name, List<ChartSong> songs) {
        return new GlobalChartDTO(name, songs.stream().map(entry -> {
            try {
                return collectionFeignProxy.getSong(entry.getSongId());
            } catch (Exception ignored) {
                return new SongDetail(entry.getSongId(), "Unknown", null, 0f);
            }
        }).toList());
    }

}
