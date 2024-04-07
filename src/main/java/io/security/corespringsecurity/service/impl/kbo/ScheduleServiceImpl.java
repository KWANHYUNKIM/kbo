package io.security.corespringsecurity.service.impl.kbo;

import io.security.corespringsecurity.domain.dto.kbo.games.ScheduleDto;
import io.security.corespringsecurity.domain.entity.kbo.games.Entrance;
import io.security.corespringsecurity.domain.entity.schedule.Location;
import io.security.corespringsecurity.domain.entity.schedule.Schedule;
import io.security.corespringsecurity.repository.kbo.games.ScheduleRepository;
import io.security.corespringsecurity.service.kbo.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Override
    public void save(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    @Override
    public void save(ScheduleDto scheduleDto) {
        Schedule schedule = convertDtoToEntity(scheduleDto);
        scheduleRepository.save(schedule);
    }

    private Schedule convertDtoToEntity(ScheduleDto scheduleDto) {
        // 여기서는 예시를 위한 간단한 변환 로직을 제공합니다.
        // 실제 구현에서는 ScheduleDto의 모든 필드를 적절히 처리해야 합니다.
        Schedule schedule = new Schedule();
        schedule.setDate(scheduleDto.getDate());
        schedule.setTime(scheduleDto.getTime());
        schedule.setHomeTeam(scheduleDto.getHomeTeam());
        schedule.setAwayTeam(scheduleDto.getAwayTeam());
        schedule.setLocation(scheduleDto.getLocationName());
        // 여기에 필요한 다른 필드 변환 로직 추가
        return schedule;
    }

    @Override
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> findByDate(String date) {
        return scheduleRepository.findByDate(date);
    }

    @Override
    public void deleteSchedule(Long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }

    @Override
    public List<Schedule> findByDateAndTeam(String date, String awayTeam, String homeTeam) {
        return scheduleRepository.findByDateAndTeam(date,awayTeam,homeTeam);
    }

    @Override
    public List<Entrance> findByEntrance(Schedule id) {
        return scheduleRepository.findByEntrance(id);
    }

    @Override
    public Schedule findByComment(Long scheduleId) {
        return scheduleRepository.findByComment(scheduleId);
    }

    @Override
    public List<Schedule> findById(Location id, String date) {
        return scheduleRepository.findByIdAndDate(id, date);
    }

    @Override
    public List<Schedule> findByDateRange(String startDate, String endDate) {
        return scheduleRepository.findByDateRange(startDate,endDate);
    }


}
