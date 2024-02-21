package io.security.corespringsecurity.domain.entity.kbo;

import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "player_record_pitcher")

public class PlayerRecordPitcher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long player_record_pitcher_id; // 새로운 기본 키

    private String date; // 경기 날짜

    private String opponent; // 상대 팀

    private String type; // 구분 (예: 리그 경기, 친선 경기 등)

    private String result; // 결과 (예: 승, 패, 무)

    private Double era1; // ERA1

    private Integer tbf; // TBF (Total Batters Faced)

    private String ip; // IP (Innings Pitched)

    private Integer h; // H (Hits)

    private Integer hr; // HR (Home Runs)

    private Integer bb; // BB (Base on Balls)

    private Integer hbp; // HBP (Hit By Pitch)

    private Integer so; // SO (Strikeouts)

    private Integer r; // R (Runs)

    private Integer er; // ER (Earned Runs)

    private Double era2; // ERA2


}