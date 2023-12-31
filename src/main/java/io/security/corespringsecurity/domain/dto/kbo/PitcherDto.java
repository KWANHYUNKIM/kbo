package io.security.corespringsecurity.domain.dto.kbo;

import io.security.corespringsecurity.domain.entity.kbo.Hitter;
import io.security.corespringsecurity.domain.entity.kbo.Pitcher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PitcherDto {

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

    public Pitcher toEntity(){
        return Pitcher.builder()
                .playerName(this.playerName)
                .teamName(this.teamName)
                .year(this.year)
                .era(this.era)
                .games(this.games)
                .wins(this.wins)
                .loses(this.loses)
                .saves(this.saves)
                .holds(this.holds)
                .winPercentage(this.winPercentage)
                .inningsPitched(this.inningsPitched)
                .hits(this.hits)
                .homeRunsAllowed(this.homeRunsAllowed)
                .walks(this.walks)
                .hitByPitch(this.hitByPitch)
                .strikeouts(this.strikeouts)
                .runsAllowed(this.runsAllowed)
                .earnedRuns(this.earnedRuns)
                .walksHitsPerInningPitched(this.walksHitsPerInningPitched)
                .build();
    }
}
