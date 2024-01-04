package io.security.corespringsecurity.service.impl.kbo;

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
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> findByDate(String date) {
        System.out.println("파라미터 date 값: " + date);
        return scheduleRepository.findByDate(date);
    }
}
