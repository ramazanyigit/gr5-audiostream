package tr.ege.edu.microservices.gr5.audiostream.popularity.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Table(name="t_global_chart")
@Entity
@Data
public class GlobalChart {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name="UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name="report_date")
    private Date reportDate;

    @ManyToOne
    @JoinColumn
    private Song song;

    @Column(name="repeat_count")
    private Long repeatCount;


}
