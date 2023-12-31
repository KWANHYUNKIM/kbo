package io.security.corespringsecurity.domain.entity.kbo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString
public class BaseballTeamStatsHitter {

    @Id
    @GeneratedValue
    @Column(name ="baseballteamstats_id")
    private long id;

    private int ranking;          // 순위
    private String teamName;      // 팀명
    private double avg;           // 평균 타율
    private int games;            // 경기 수
    private int plateAppearances; // 타석 수
    private int atBats;           // 타수
    private int runs;             // 득점
    private int hits;             // 안타 수
    private int doubles;          // 2루타 수
    private int triples;          // 3루타 수
    private int homeRuns;         // 홈런 수
    private int totalBases;       // 총 루타
    private int rbis;             // 타점
    private int sacrificeHits;    // 희생 번트 수
    private int sacrificeFlies;   // 희생 플라이 수
}
