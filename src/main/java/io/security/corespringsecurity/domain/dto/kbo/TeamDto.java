package io.security.corespringsecurity.domain.dto.kbo;

import io.security.corespringsecurity.domain.entity.kbo.TeamEntity;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {

    private Long id;
    private int ranking;
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
}
