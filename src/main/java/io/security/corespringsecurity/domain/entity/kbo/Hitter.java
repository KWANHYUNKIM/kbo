package io.security.corespringsecurity.domain.entity.kbo;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString
public class Hitter {
    @Id
    @GeneratedValue
    @Column(name ="hitter_id")
    private long id;

    private String playerName; //선수명
    private String teamName; // 팀명
    private int year; // 년도
    private double avg ; // 타율
    private int games; // 게임수
    private int plateAppearances; //타석
    private int atBats; // 타수
    private int runs; // 득점
    private int hits; //안타
    private int doubles; // 2루타
    private int triples; // 3루타
    private int homeRuns; // 홈런
    private int totalBases; // 루타
    private int rbi; // 타점
    private int sac; // 희생 번트
    private int sf; // 희생 플레이
}
