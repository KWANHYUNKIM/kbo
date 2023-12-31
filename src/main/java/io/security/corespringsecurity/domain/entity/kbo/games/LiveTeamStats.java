package io.security.corespringsecurity.domain.entity.kbo.games;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString
public class LiveTeamStats {
    @Id
    @GeneratedValue
    @Column(name ="teamstats_id")
    private Long id;

    private String teamName;
    private int wins;
    private int losses;
    private int draws;

    @ElementCollection
    private List<Integer> inningScores;

    private int runs;
    private int hits;
    private int errors;
    private int atBats;
    private Date gameDate;
}