package io.security.corespringsecurity.domain.entity.kbo;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Data
@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "player_records")

public class PlayerRecord {


    @Id
    @Column(name = "player_records_id")
    private Long player_records_id;

    @ManyToOne
    @JoinColumn(name = "players_id")
    private Players players;

    // 경기를 하면서 자연스럽게 축척되는 데이터
    @OneToOne(optional = true)
    @JoinColumn(name = "player_record_hitter_id")
    private PlayerRecordHitter playerRecordHitter;

    @OneToOne(optional = true)
    @JoinColumn(name = "player_record_hitter_detail_id")
    private DetailsRecordHitter detailsRecordHitter;

    @OneToOne(optional = true)
    @JoinColumn(name = "player_record_pitcher_id")
    private PlayerRecordPitcher playerRecordPitcher;

    @OneToOne(optional = true)
    @JoinColumn(name = "player_record_pitcher_detail_id")
    private DetailsRecordPitcher detailsRecordPitcher;

}