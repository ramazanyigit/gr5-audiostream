package tr.ege.edu.microservices.gr5.audiostream.popularity.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tr.ege.edu.microservices.gr5.audiostream.popularity.common.CollectionFeignProxy;
import tr.ege.edu.microservices.gr5.audiostream.popularity.model.KafkaEvent;
import tr.ege.edu.microservices.gr5.audiostream.popularity.model.GlobalChart;
import tr.ege.edu.microservices.gr5.audiostream.popularity.repository.GlobalChartRepository;
import tr.ege.edu.microservices.gr5.audiostream.popularity.type.Genre;

import java.time.temporal.ChronoUnit;

@Service
@Slf4j
@AllArgsConstructor
public class KafkaConsumer {
    private final CollectionFeignProxy collectionFeignProxy;
    private final GlobalChartRepository globalChartRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "streaming")
    public void consumeStreaming(JsonNode message) throws JsonProcessingException {
        var event = objectMapper.treeToValue(message, KafkaEvent.class);

        if (!event.type().equals("START")) {
            return;
        }

        var streamLog = event.payload();
        var songDetail = collectionFeignProxy.getSong(streamLog.songId());
        var date = streamLog.creationTimestamp().truncatedTo(ChronoUnit.DAYS);
        var chartOptional = globalChartRepository.findBySongIdAndReportDate(streamLog.songId(), date);

        GlobalChart chart = new GlobalChart();

        if (chartOptional.isPresent()) {
            chart = chartOptional.get();
        } else {
            chart.setRepeatCount(0L);
            chart.setSongId(streamLog.songId());

            chart.setReportDate(date);
        }

        chart.setGenre(Genre.valueOf(songDetail.album().genre()));
        chart.setRepeatCount(chart.getRepeatCount() + 1L);

        globalChartRepository.save(chart);
    }
}
