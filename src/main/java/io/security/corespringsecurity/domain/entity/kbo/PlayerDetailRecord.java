package io.security.corespringsecurity.domain.entity.kbo;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDetailRecord {
    @Id
    @Column(name = "player_details_record_id")
    private Long player_details_record_id;

    @ManyToOne
    @JoinColumn(name = "players_id")
    private Players players;

    @OneToOne(optional = true)
    @JoinColumn(name = "player_record_hitter_id")
    private DetailsRecordHitter detailsRecordHitter;

    @OneToOne(optional = true)
    @JoinColumn(name = "player_record_pitcher_id")
    private DetailsRecordPitcher detailsRecordPitcher;
}
