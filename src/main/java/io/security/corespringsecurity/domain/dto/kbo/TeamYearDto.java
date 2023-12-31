package io.security.corespringsecurity.domain.dto.kbo;

import io.security.corespringsecurity.domain.entity.kbo.Hitter;
import io.security.corespringsecurity.domain.entity.kbo.TeamYear;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamYearDto {

    private Long id;

    private String ranking;
    private String teamName;
    private int matches;
    private int win;
    private int loss;
    private int draw;
    private double winningPercentage;
    private String gameDifference;
    private String recent10Games;
    private String streak;
    private String home;
    private String away;
    private String year;

    public TeamYear toEntity(){
        return TeamYear.builder()
                .ranking(this.ranking)
                .teamName(this.teamName)
                .matches(this.matches)
                .win(this.win)
                .loss(this.loss)
                .draw(this.draw)
                .winningPercentage(this.winningPercentage)
                .gameDifference(this.gameDifference)
                .recent10Games(this.recent10Games)
                .streak(this.streak)
                .home(this.home)
                .away(this.away)
                .year(this.year)
                .build();
    }

}
