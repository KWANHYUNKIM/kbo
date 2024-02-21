package io.security.corespringsecurity.domain.dto.kbo.games;

import io.security.corespringsecurity.domain.entity.kbo.Players;
import io.security.corespringsecurity.domain.entity.kbo.Teams;
import io.security.corespringsecurity.domain.entity.kbo.games.Entrance;
//import io.security.corespringsecurity.domain.entity.kbo.games.EntrancePlayer;
import io.security.corespringsecurity.domain.entity.schedule.Schedule;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EntranceDto {

    private Long id; // Entrance 엔터티의 ID

    private Teams teams;

    private Players entrancePlayer;

    private String gameNumber;

    private Schedule schedule;

    public Entrance toEntity() {
        return Entrance.builder()
                .teams(teams)
                .gameNumber(gameNumber)
                .schedule(schedule)
                .build();

    }
}
