package io.security.corespringsecurity.domain.entity.kbo.games;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString
public class LivePitcher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String result;
    private String position;
    private int win;
    private int lose;
    private int save;
    private double innings;
    private int battersFaced;
    private int pitchesThrown;
    private int hitsAllowed;
    private int homeRunsAllowed;
    private int walks;
    private int strikeouts;
    private int runs;
    private int earnedRuns;
    private double ERA;
}