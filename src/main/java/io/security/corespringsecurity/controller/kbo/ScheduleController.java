package io.security.corespringsecurity.controller.kbo;

import io.security.corespringsecurity.domain.entity.schedule.Schedule;
import io.security.corespringsecurity.service.kbo.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping(value = "/schedule/weather")
    public String scheduleWeather(){

        return "schedule/scheduleWeather";
    }
    @GetMapping(value = "/schedule/scoreboard")
    public String scheduleScoreBoard(){
        return "schedule/scheduleScoreBoard";
    }

    @GetMapping (value = "/api/schedule")
    public String findByDate(@RequestParam(name = "date") String date, Model model){
        System.out.println("data 값은?" + date);
        List<Schedule> schedule = scheduleService.findByDate(date);
        System.out.println("schedule 값은?" + schedule);

        if (schedule.isEmpty()) {
            model.addAttribute("noGameMessage", "오늘 경기가 없습니다");
        } else {
            model.addAttribute("scheduleLists", schedule);
        }

        return "home";
    }

}
