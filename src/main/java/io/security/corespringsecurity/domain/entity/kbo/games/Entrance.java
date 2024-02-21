package io.security.corespringsecurity.domain.entity.kbo.games;

import io.security.corespringsecurity.domain.entity.kbo.Players;
import io.security.corespringsecurity.domain.entity.kbo.Teams;
import io.security.corespringsecurity.domain.entity.schedule.Schedule;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "entrance")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Entrance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entrance_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "teams_id")
    private Teams teams;

    @Column(name = "game_number") // 게임 식별자 추가
    private String gameNumber;

    @ManyToOne
    @JoinColumn(name = "schedule_id") // Schedule 엔터티와의 관계
    private Schedule schedule;

    @OneToMany(mappedBy = "entrance")
    private List<EntrancePlayer> entrancePlayers = new ArrayList<EntrancePlayer>();

}
