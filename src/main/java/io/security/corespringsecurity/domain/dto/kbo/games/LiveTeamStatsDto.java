package io.security.corespringsecurity.domain.dto.kbo.games;

import io.security.corespringsecurity.domain.entity.kbo.games.LiveTeamStats;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LiveTeamStatsDto {
    private Long id;
    private String teamName;
    private int wins;
    private int losses;
    private int draws;
    private List<Integer> inningScores;
    private int runs;
    private int hits;
    private int errors;
    private int atBats;
    private Date gameDate;

    public LiveTeamStats toEntity() {
        return LiveTeamStats.builder()
                .id(this.id)
                .teamName(this.teamName)
                .wins(this.wins)
                .losses(this.losses)
                .draws(this.draws)
                .inningScores(this.inningScores)
                .runs(this.runs)
                .hits(this.hits)
                .atBats(this.atBats)
                .gameDate(this.gameDate)
                .build();
    }
}
