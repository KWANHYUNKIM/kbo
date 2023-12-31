package io.security.corespringsecurity.domain.dto.kbo;

import io.security.corespringsecurity.domain.entity.kbo.Hitter;
import io.security.corespringsecurity.domain.entity.kbo.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
                .build();
    }
}
