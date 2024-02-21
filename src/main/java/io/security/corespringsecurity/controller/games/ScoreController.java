package io.security.corespringsecurity.controller.games;

import io.security.corespringsecurity.domain.entity.schedule.Schedule;
import io.security.corespringsecurity.service.kbo.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScoreController {

    @Autowired
    private ScheduleService scheduleService;


    @PostMapping("/api/save-score/{scheduleId}")
    public void saveScore(@PathVariable Long scheduleId,
                          @RequestParam("homeScore") int homeScore,
                          @RequestParam("awayScore") int awayScore) {

        Schedule schedule = scheduleService.findByComment(scheduleId);

        // homeScore와 awayScore의 값을 사용하여 각 팀의 점수를 증가시킵니다.
        schedule.setHomeTeamCheerScore(homeScore);
        schedule.setAwayTeamCheerScore(awayScore);

        // 변경된 점수를 DB에 저장
        scheduleService.save(schedule);
    }

}