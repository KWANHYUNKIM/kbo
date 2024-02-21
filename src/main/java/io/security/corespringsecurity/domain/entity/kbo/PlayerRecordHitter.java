package io.security.corespringsecurity.domain.entity.kbo;

import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "player_record_hitter")

public class PlayerRecordHitter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long player_record_hitter_id; // 새로운 기본 키

    private String date;
    private String opponent;
    private double avg1;
    private int pa;
    private int ab;
    private int r;
    private int h;
    private int doubles;
    private int triples;
    private int hr;
    private int rbi;
    private int sb;
    private int cs;
    private int bb;
    private int hbp;
    private int so;
    private int gdp;
    private double avg2;
}

