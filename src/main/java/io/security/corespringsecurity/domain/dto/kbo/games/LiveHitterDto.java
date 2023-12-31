package io.security.corespringsecurity.domain.dto.kbo.games;

import io.security.corespringsecurity.domain.entity.kbo.games.LiveHitter;
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
public class LiveHitterDto {
    private Long id;
    private String position;
    private String name;
    private List<String> inningScores;
    private int atBats;
    private int hits;
    private int RBIs;
    private int runs;
    private double battingAverage;
    private Date gameDate;
    private String teams;

    public LiveHitter toEntity(){
        return LiveHitter.builder()
                .id(this.id)
                .position(this.position)
                .name(this.name)
                .inningScores(this.inningScores)
                .atBats(this.atBats)
                .hits(this.hits)
                .runs(this.runs)
                .battingAverage(this.battingAverage)
                .gameDate(this.gameDate)
                .teams(this.teams)
                .build();
    }
}
