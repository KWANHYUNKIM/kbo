package io.security.corespringsecurity.domain.dto.kbo.games;

import io.security.corespringsecurity.domain.entity.kbo.games.LivePitcher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LivePitcherDto {
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

    public LivePitcher toEntity(){
        return LivePitcher.builder()
                .id(this.id)
                .name(this.name)
                .result(this.result)
                .position(this.position)
                .win(this.win)
                .lose(this.lose)
                .save(this.save)
                .innings(this.innings)
                .battersFaced(this.battersFaced)
                .pitchesThrown(this.pitchesThrown)
                .hitsAllowed(this.hitsAllowed)
                .homeRunsAllowed(this.homeRunsAllowed)
                .walks(this.walks)
                .strikeouts(this.strikeouts)
                .runs(this.runs)
                .earnedRuns(this.earnedRuns)
                .ERA(this.ERA)
                .build();
    }
}
