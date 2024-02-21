package io.security.corespringsecurity.domain.entity.kbo.games;

import io.security.corespringsecurity.domain.entity.schedule.Schedule;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class GameBoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_board_id")
    private List<InningScore> inningScores = new ArrayList<>();

    private  String crowd;

    private String startTime;

    private String endTime;

    private String runTime;

    private String homeTeamResult;

    private String awayTeamResult;

    // 우리 팀의 R, H, E, B
    private int homeTeamR;
    private int homeTeamH;
    private int homeTeamE;
    private int homeTeamB;

    // 적 팀의 R, H, E, B
    private int awayTeamR;
    private int awayTeamH;
    private int awayTeamE;
    private int awayTeamB;

    @OneToOne(mappedBy = "gameBoard")
    private GameHighlights gameHighlights;

    @OneToOne(mappedBy = "gameBoard")
    private Schedule schedule;

    // 홈 팀의 타자 통계
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gameBoard")
    private List<HitterStats> teamStats = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gameBoard")
    private List<PitcherStats> pitcherStats = new ArrayList<>();

    public void setInningScores(List<InningScore> inningScores) {
        this.inningScores.clear(); // 기존 리스트를 비웁니다.
        this.inningScores.addAll(inningScores); // 새로운 리스트의 모든 요소를 추가합니다.
    }

}

