package io.security.corespringsecurity.repository.kbo.games;

import io.security.corespringsecurity.domain.entity.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    @Query("SELECT s FROM Schedule s WHERE s.date = :date")
    List<Schedule> findByDate(@Param("date") String date);
}
