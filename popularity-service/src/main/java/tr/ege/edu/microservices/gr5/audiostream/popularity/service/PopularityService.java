package tr.ege.edu.microservices.gr5.audiostream.popularity.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import tr.ege.edu.microservices.gr5.audiostream.popularity.dto.GlobalChartDTO;
import tr.ege.edu.microservices.gr5.audiostream.popularity.model.*;
import tr.ege.edu.microservices.gr5.audiostream.popularity.repository.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PopularityService {
    private final SongDailyRecordRepository songDailyRecordRepository;
    private final SongRepository songRepository;
    private final GlobalChartRepository globalChartRepository;




    @Autowired
    public PopularityService(SongDailyRecordRepository songDailyRecordRepository, SongRepository songRepository,
                             GlobalChartRepository globalChartRepository){
        this.songDailyRecordRepository = songDailyRecordRepository;
        this.songRepository = songRepository;
        this.globalChartRepository=globalChartRepository;


    }

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


    public List<GlobalChartDTO> getGlobalTopTenList(){
        List<GlobalChart> globalChart=globalChartRepository.getGlobalTopTen();
        //Collection-service, get SongName
        return null;
    }

    public List<GlobalChartDTO> getGlobalTopHundredList(){
        List<GlobalChart> globalChart=globalChartRepository.getGlobalTopHundred();
        //Collection-service, get SongName
        return null;
    }

    public List<Song> getGenreTopTen(UUID genreId){
        List<GlobalChart> globalChartByGenre=globalChartRepository.getGenreTopTen(genreId);
        List<Song> genreTopSongs=new ArrayList<>();
        for (GlobalChart topSong:
             globalChartByGenre ) {
            genreTopSongs.add(topSong.getSong());
        }
        return genreTopSongs;
    }








}
