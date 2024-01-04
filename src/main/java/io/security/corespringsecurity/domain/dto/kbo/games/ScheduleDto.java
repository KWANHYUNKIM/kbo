package io.security.corespringsecurity.domain.dto.kbo.games;

import io.security.corespringsecurity.domain.entity.kbo.Teams;
import io.security.corespringsecurity.domain.entity.schedule.Location;
import io.security.corespringsecurity.domain.entity.schedule.Schedule;
import lombok.*;

import javax.persistence.Id;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScheduleDto {

    private long id;
    private String date;
    private String time;
    private Teams homeTeam;
    private Teams awayTeam;
    private Location locationName;
    private List<Schedule> schedules; // 추가

    public Schedule toEntity(){
        return Schedule.builder()
                .date(this.date)
                .time(this.time)
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .location(locationName)
                .build();
    }
}
