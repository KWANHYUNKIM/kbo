package io.security.corespringsecurity.domain.dto.kbo.crawl;

import io.security.corespringsecurity.domain.entity.kbo.crawl.Player;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlayerDto {

    private long id;
    private String jerseyNumber;
    private String name;
    private String team;
    private String position;
    private String birthDate;
    private String height;
    private String weight;
    private String careerHistory;
    private Integer debutYear;
    private String draftRank;
    private Integer annualSalary;
    private Integer signingBonus;
    private String filepath;
    private String filename;


    public Player toEntity(){
        return Player.builder()
                .jerseyNumber(this.jerseyNumber)
                .name(this.name)
                .team(this.team)
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
