package tr.ege.edu.microservices.gr5.audiostream.popularity.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tr.ege.edu.microservices.gr5.audiostream.popularity.common.CollectionFeignProxy;
import tr.ege.edu.microservices.gr5.audiostream.popularity.dto.KafkaEvent;
import tr.ege.edu.microservices.gr5.audiostream.popularity.dto.SongDetail;
import tr.ege.edu.microservices.gr5.audiostream.popularity.dto.StreamingLog;
import tr.ege.edu.microservices.gr5.audiostream.popularity.model.GlobalChart;
import tr.ege.edu.microservices.gr5.audiostream.popularity.model.Song;
import tr.ege.edu.microservices.gr5.audiostream.popularity.repository.GlobalChartRepository;
import tr.ege.edu.microservices.gr5.audiostream.popularity.repository.SongRepository;
import tr.ege.edu.microservices.gr5.audiostream.popularity.type.Genre;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class KafkaConsumer {
    private final CollectionFeignProxy collectionFeignProxy;
    private final SongRepository songRepository;
    private final GlobalChartRepository globalChartRepository;


    @KafkaListener(topics = "collection")
    public void consumeCollection(String message) throws IOException {
        log.info(String.format("# Consumed message: %s", message));
    }
    @KafkaListener(topics = "streaming")
    public void consumeStreaming(KafkaEvent message) throws  IOException{
        KafkaEvent event=message;
        StreamingLog payload=(StreamingLog) event.getPayload();
        SongDetail song= collectionFeignProxy.getSong(payload.songId());
        Song newSong=new Song();
        newSong.setId(song.id());
        newSong.setGenre(Genre.valueOf(song.album().genre()));
        boolean exist=songRepository.existsById(song.id());
        if(exist){
            GlobalChart chart= globalChartRepository.findBySongId(song.id());
            chart.setRepeatCount(chart.getRepeatCount()+1);
        }else{
            songRepository.save(newSong);
            GlobalChart newChart=new GlobalChart();
            newChart.setRepeatCount(1L);
            newChart.setSong(newSong);
            newChart.setReportDate(new Date());
            globalChartRepository.save(newChart);
        }
    }
}
