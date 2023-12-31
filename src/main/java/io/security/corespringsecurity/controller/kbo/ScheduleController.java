package io.security.corespringsecurity.controller.kbo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScheduleController {

    @GetMapping(value = "/schedule/weather")
    public String scheduleWeather(){

        return "schedule/scheduleWeather";
    }
    @GetMapping(value = "/schedule/scoreboard")
    public String scheduleScoreBoard(){
        return "schedule/scheduleScoreBoard";
    }
}
