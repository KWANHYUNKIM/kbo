package io.security.corespringsecurity.domain.entity.kbo.games;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "hitter_stats")
public class HitterStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "batting_order")
    private String battingOrder;

    @Column(name = "defensive_position")
    private String defensivePosition;

    @Column(name = "player_name")
    private String playerName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "hitter_id")
    private List<InningHitterResult> inningHitterResults = new ArrayList<>();

    @Column(name = "at_bats")
    private Integer atBats;

    @Column(name = "hits")
    private Integer hits;

    private Integer runsBattedIn;

    @Column(name = "runs")
    private Integer runs;

    @Column(name = "batting_average")
    private Double battingAverage;

    private String teamType; // "home" 또는 "away" 값 저장

    @ManyToOne
    @JoinColumn(name = "game_board_id", referencedColumnName = "id")
    private GameBoardEntity gameBoard;

    // 생성자, 게터, 세터 등은 생략
    public void setInningResults(List<InningHitterResult> inningHitterResults) {
        this.inningHitterResults.clear(); // 기존 리스트를 비웁니다.
        this.inningHitterResults.addAll(inningHitterResults); // 새로운 리스트의 모든 요소를 추가합니다.
    }
}
