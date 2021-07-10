package tr.ege.edu.microservices.gr5.audiostream.popularity.service;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.ege.edu.microservices.gr5.audiostream.popularity.common.CollectionFeignProxy;
import tr.ege.edu.microservices.gr5.audiostream.popularity.dto.GlobalChartDTO;
import tr.ege.edu.microservices.gr5.audiostream.popularity.dto.SongDetail;
import tr.ege.edu.microservices.gr5.audiostream.popularity.model.*;
import tr.ege.edu.microservices.gr5.audiostream.popularity.repository.*;
import tr.ege.edu.microservices.gr5.audiostream.popularity.type.Genre;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@AllArgsConstructor
public class PopularityService {
    private final SongDailyRecordRepository songDailyRecordRepository;
    private final GlobalChartRepository globalChartRepository;
    private final CollectionFeignProxy collectionFeignProxy;


   /* @Autowired
    public PopularityService(SongDailyRecordRepository songDailyRecordRepository, SongRepository songRepository,
                             GlobalChartRepository globalChartRepository, CollectionFeignProxy collectionFeignProxy){
        this.songDailyRecordRepository = songDailyRecordRepository;
        this.songRepository = songRepository;
        this.globalChartRepository=globalChartRepository;
        this.collectionFeignProxy=collectionFeignProxy;
    }*/

    public void fillGlobalChart() throws ParseException {
        List<SongDailyRecord> songDailyRecords=
                songDailyRecordRepository.getByReportDate(new SimpleDateFormat("yyyy-MM-dd").parse(new Date().toString()));
        for (SongDailyRecord dailyRecord:
             songDailyRecords) {
            GlobalChart temp=globalChartRepository.findBySongId(dailyRecord.getSong().getId());
            if(temp!=null){
                temp.setRepeatCount(temp.getRepeatCount()+dailyRecord.getDailyRepeatCount());
            }else{
                GlobalChart newGlobalChart=new GlobalChart();
                newGlobalChart.setRepeatCount(dailyRecord.getDailyRepeatCount());
                newGlobalChart.setSong(dailyRecord.getSong());
                newGlobalChart.setReportDate(new SimpleDateFormat("yyyy-MM-dd").parse(new Date().toString()));
                globalChartRepository.save(newGlobalChart);
            }
        }
    }


    public GlobalChartDTO getGlobalTopTenList(){
        List<GlobalChart> globalChart=globalChartRepository.getGlobalTopTen();
        GlobalChartDTO chart=new GlobalChartDTO();
        chart.listName="Top 10";
        List<SongDetail> topSongs=new ArrayList<>();
        for (GlobalChart topSong:
                globalChart ) {
            SongDetail song=collectionFeignProxy.getSong(topSong.getSong().getId());
            topSongs.add(song);
        }
        chart.songs=topSongs;
        return chart;
    }

    public GlobalChartDTO getGlobalTopHundredList(){
        List<GlobalChart> globalChart=globalChartRepository.getGlobalTopHundred();
        GlobalChartDTO chart=new GlobalChartDTO();
        chart.listName="Top 100";
        List<SongDetail> topSongs=new ArrayList<>();
        for (GlobalChart topSong:
                globalChart ) {
            SongDetail song=collectionFeignProxy.getSong(topSong.getSong().getId());
            topSongs.add(song);
        }
        chart.songs=topSongs;
        return chart;
    }

    public GlobalChartDTO getGenreTop(Genre genre){
        List<GlobalChart> globalChartByGenre=globalChartRepository.getGenreTop(genre);
        GlobalChartDTO chart=new GlobalChartDTO();
        chart.listName="Best " + genre.toString() + "Songs";
        List<SongDetail> genreTopSongs=new ArrayList<>();
        for (GlobalChart topSong:
             globalChartByGenre ) {
            SongDetail song=collectionFeignProxy.getSong(topSong.getSong().getId());
            genreTopSongs.add(song);
        }
        chart.songs=genreTopSongs;
        return chart;
    }

}
