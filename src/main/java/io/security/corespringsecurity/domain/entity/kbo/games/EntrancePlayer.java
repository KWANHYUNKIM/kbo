package io.security.corespringsecurity.domain.entity.kbo.games;
import io.security.corespringsecurity.domain.entity.kbo.Players;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter

public class EntrancePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "entrance_id")
    private Entrance entrance;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Players player;
}
