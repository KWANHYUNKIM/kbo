package io.security.corespringsecurity.domain.entity.kbo.games;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class InningScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int inning; // 이닝 번호 (1회 ~ 12회)
    private String homeTeamScore; // 우리 팀 점수
    private String awayTeamScore; // 적 팀 점수

    // 생성자, 게터, 세터 생략

    public InningScore(int inning, String homeTeamScore, String awayTeamScore) {
        this.inning = inning;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }
}
