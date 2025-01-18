package io.security.corespringsecurity.domain.entity.kbo.games;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GameHighlights {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 결승타
    private String gameWinningHit;
    // 홈런
    private String homeRun;
    // 에러
    private String error;
    // 도루
    private String stolenBase;
    // 병살타
    private String doublePlay;
    // 심판
    private String umpire;
    // 2루타
    private String doubleHit;
    // 주루사
    private String baseRunningOut;
    // 견제사
    private String pickOff;
    // 3루타
    private String triple;
    // 폭투
    private String wildPitch;

    @OneToOne
    @JoinColumn(name = "game_board_id")
    private GameBoardEntity gameBoard;    
    
    public void setGameWinningHit(String gameWinningHit) {
        this.gameWinningHit = gameWinningHit;
    }
    // 나머지 세터 메서드 생략...
    public void matchAndSetData(String header, String data) {
        switch (header) {
            case "결승타":
                setGameWinningHit(data);
                break;
            case "홈런":
                setHomeRun(data);
                break;
            case "실책":
                setError(data);
                break;
            case "도루":
                setStolenBase(data);
                break;
            case "병살타":
                setDoublePlay(data);
                break;
            case "심판":
                setUmpire(data);
                break;
            case "2루타":
                setDoubleHit(data);
                break;
            case "주루사":
                setBaseRunningOut(data);
                break;
            // "견제사"와 "3루타"에 대한 케이스 처리는 필드가 정의되어 있지 않으므로 생략
            case "폭투":
                setWildPitch(data);
                break;
            // 추가적인 케이스 처리...
            default:
                // 처리되지 않은 헤더에 대한 로직
                break;
        }
    }
}
