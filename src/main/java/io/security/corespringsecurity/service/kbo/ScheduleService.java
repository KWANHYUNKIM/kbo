package io.security.corespringsecurity.service.kbo;

import io.security.corespringsecurity.domain.dto.kbo.games.ScheduleDto;
import io.security.corespringsecurity.domain.entity.kbo.games.Entrance;
import io.security.corespringsecurity.domain.entity.schedule.Location;
import io.security.corespringsecurity.domain.entity.schedule.Schedule;

import java.util.List;

public interface ScheduleService {
    void save(Schedule schedule);

    void save(ScheduleDto scheduleDto);

    List<Schedule> findAll();

    List<Schedule> findByDate(String date);


    void deleteSchedule(Long scheduleId);

    List<Schedule> findByDateAndTeam(String date, String awayTeam, String homeTeam);

    List<Entrance> findByEntrance(Schedule id);

    Schedule findByComment(Long scheduleId);

    List<Schedule> findById(Location id, String date);
}
