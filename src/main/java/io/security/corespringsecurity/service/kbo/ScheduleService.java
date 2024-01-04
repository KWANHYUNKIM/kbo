package io.security.corespringsecurity.service.kbo;

import io.security.corespringsecurity.domain.entity.schedule.Schedule;

import java.util.List;

public interface ScheduleService {
    void save(Schedule schedule);

    List<Schedule> findAll();

    List<Schedule> findByDate(String date);

}
