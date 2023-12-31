package io.security.corespringsecurity.domain.dto.kbo;

import io.security.corespringsecurity.domain.entity.kbo.Hitter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HitterDto {

    private String playerName; //선수명
    private String teamName; // 팀명
    private int year; // 년도
    private double avg ; // 타율
    private int games; // 게임수
    private int plateAppearances; //타석
    private int atBats; // 타수
    private int runs; // 득점
    private int hits; //안타
    private int doubles; // 2루타
    private int triples; // 3루타
    private int homeRuns; // 홈런
    private int totalBases; // 루타
    private int rbi; // 타점
    private int sac; // 희생 번트
    private int sf; // 희생 플레이

    public Hitter toEntity(){
        return Hitter.builder()
                .playerName(this.playerName)
                .teamName(this.teamName)
                .year(this.year)
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
                .rbi(this.rbi)
                .sac(this.sac)
                .sf(this.sf)
                .build();
    }
}
