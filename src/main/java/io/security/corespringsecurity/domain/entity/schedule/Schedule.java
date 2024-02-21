package io.security.corespringsecurity.domain.entity.schedule;


import io.security.corespringsecurity.domain.entity.kbo.Teams;
import io.security.corespringsecurity.domain.entity.kbo.games.Entrance;
import io.security.corespringsecurity.domain.entity.kbo.games.GameBoardEntity;
import io.security.corespringsecurity.domain.entity.kbo.games.GameComment;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "schedule")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "schedule", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<GameComment> gameComments;

    private int HomeTeamCheerScore;

    private int AwayTeamCheerScore;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_board_id", referencedColumnName = "id")
    private GameBoardEntity gameBoard;

}
