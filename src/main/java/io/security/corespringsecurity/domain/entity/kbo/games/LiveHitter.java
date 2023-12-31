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
public class LiveHitter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String position;
    private String name;
    @ElementCollection
    private List<String> inningScores;
    private int atBats;
    private int hits;
    private int RBIs;
    private int runs;
    private double battingAverage;
    private Date gameDate;
    private String teams;

}
