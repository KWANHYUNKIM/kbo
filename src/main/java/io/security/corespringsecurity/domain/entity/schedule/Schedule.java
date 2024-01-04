package io.security.corespringsecurity.domain.entity.schedule;


import io.security.corespringsecurity.domain.entity.kbo.Teams;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Data
@Table(name = "schedule")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Schedule {
    @Id
    @GeneratedValue
    @Column(name ="schedule_id")
    private Long id;

    @Column(name = "date")
    private String date;
    @Column(name = "time")
    private String time;

    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private Teams homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id")
    private Teams awayTeam;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;


}
