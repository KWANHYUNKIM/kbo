package io.security.corespringsecurity.domain.dto.kbo.games;

import io.security.corespringsecurity.domain.entity.kbo.Players;
import io.security.corespringsecurity.domain.entity.kbo.Teams;
import io.security.corespringsecurity.domain.entity.kbo.crawl.Player;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlayersDto {
    private long id;
    private String jerseyNumber;
    private String name;
    private Teams team;
    private String position;
    private String birthDate;
    private String height;
    private String weight;
    private String careerHistory;
    private String debutYear;
    private String draftRank;
    private Integer annualSalary;
    private Integer signingBonus;
    private String filepath;
    private String filename;


    public Players toEntity(){
        return Players.builder()
                .jerseyNumber(this.jerseyNumber)
                .name(this.name)
                .teams(this.team)
                .position(this.position)
                .birthDate(this.birthDate)
                .height(this.height)
                .weight(this.weight)
                .careerHistory(this.careerHistory)
                .debutYear(this.debutYear)
                .draftRank(this.draftRank)
                .annualSalary(this.annualSalary)
                .signingBonus(this.signingBonus)
                .build();
    }
}
