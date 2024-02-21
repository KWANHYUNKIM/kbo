package io.security.corespringsecurity.domain.entity.kbo.games;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "pitcher_stats")
public class PitcherStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String playerName; // 선수
    private String appearances; // 등판 횟수
    private String result; // 등판 결과
    private int wins; // 승
    private int losses; // 패
    private int saves; // 세이브
    private String inningsPitched; // 투구 이닝수
    private int battersFaced; // 타자
    private int pitchesThrown; // 투구수
    private int atBats; // 타수
    private int hitsAllowed; // 안타 수
    private int homeRunsAllowed; // 홈런 수
    private int walks; // 볼넷 수
    private int strikeouts; // 삼진 수

    private int runsAllowed; // 실점
    private int earnedRuns; // 자책
    private String earnedRunAverage; // 평균자책점

    private String teamType; // "home" 또는 "away" 값 저장

    @ManyToOne
    @JoinColumn(name = "game_board_id", referencedColumnName = "id")
    private GameBoardEntity gameBoard;


}
