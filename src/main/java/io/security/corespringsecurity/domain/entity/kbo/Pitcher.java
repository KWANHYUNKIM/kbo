package io.security.corespringsecurity.domain.entity.kbo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString
public class Pitcher {
    @Id
    @GeneratedValue
    @Column(name ="pitcher_id")
    private long id;

    private String playerName; // 선수명
    private String teamName; // 팀명
    private int year; // 년도
    private double era; // 평균자책점
    private int games; // 경기수
    private int wins; // 승리
    private int loses; // 패배
    private int saves; // 세이브
    private int holds; // 홀드
    private double winPercentage; // 승률
    private String inningsPitched; // 이닝
    private int hits; // 피안타
    private int homeRunsAllowed; // 피홈런
    private int walks; // 볼넷
    private int hitByPitch; // 사구
    private int strikeouts; // 삼진
    private int runsAllowed; // 실점
    private int earnedRuns; // 자책점
    private double walksHitsPerInningPitched; // 이닝당 출허용률
}
