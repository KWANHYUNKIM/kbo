package io.security.corespringsecurity.repository.kbo.games;

import io.security.corespringsecurity.domain.dto.kbo.games.ScheduleDto;
import io.security.corespringsecurity.domain.entity.kbo.games.Entrance;
import io.security.corespringsecurity.domain.entity.schedule.Location;
import io.security.corespringsecurity.domain.entity.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    @Query("SELECT s FROM Schedule s WHERE s.date = :date")
    List<Schedule> findByDate(@Param("date") String date);

    @Query("SELECT s FROM Schedule s WHERE s.date = :date AND (s.awayTeam.teamName = :awayTeam OR s.homeTeam.teamName = :homeTeam)")
    List<Schedule> findByDateAndTeam(
            @Param("date") String date,
            @Param("awayTeam") String awayTeam,
            @Param("homeTeam") String homeTeam
    );
    @Query("SELECT e FROM Entrance e WHERE e.schedule = :id")
    List<Entrance> findByEntrance(@Param("id") Schedule id);


    @Query("SELECT s FROM Schedule s WHERE s.id = :id")
    Schedule findByComment(@Param("id") Long scheduleId);

    @Query("SELECT s FROM Schedule s WHERE s.location = :id AND s.date = :date")
    List<Schedule> findByIdAndDate(@Param("id") Location id, @Param("date") String date);

    @Query("SELECT s FROM Schedule s WHERE s.date BETWEEN :startDate AND :endDate")
    List<Schedule> findByDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);
}
