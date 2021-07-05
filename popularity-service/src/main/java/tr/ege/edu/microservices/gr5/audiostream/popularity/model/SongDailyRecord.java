package tr.ege.edu.microservices.gr5.audiostream.popularity.model;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Table(name="t_song_record")
@Entity
@Data
public class SongDailyRecord {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name="UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name="report_date")
    private Date reportDate;

    @Column
    private UUID userId;

    @ManyToOne
    @JoinColumn
    private Song song;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name="daily_repeat_count")
    private Long dailyRepeatCount;

}
