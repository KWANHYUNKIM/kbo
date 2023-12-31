package io.security.corespringsecurity.domain.dto.kbo;

import io.security.corespringsecurity.domain.entity.kbo.BaseballTeamStatsHitter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseballTeamStatsHitterDto {

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
    public BaseballTeamStatsHitter toEntity() {
        return BaseballTeamStatsHitter.builder()
                .ranking(this.ranking)
                .teamName(this.teamName)
                .avg(this.avg)
                .games(this.games)
                .plateAppearances(this.plateAppearances)
                .atBats(this.atBats)
                .runs(this.runs)
                .hits(this.hits)
                .doubles(this.doubles)
                .triples(this.triples)
                .homeRuns(this.homeRuns)
                .totalBases(this.totalBases)
                .rbis(this.rbis)
                .sacrificeHits(this.sacrificeHits)
                .sacrificeFlies(this.sacrificeFlies)
                .build();
    }
}
