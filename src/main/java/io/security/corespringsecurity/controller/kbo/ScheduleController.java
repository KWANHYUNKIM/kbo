package io.security.corespringsecurity.controller.kbo;

import io.security.corespringsecurity.domain.entity.schedule.Schedule;
import io.security.corespringsecurity.service.kbo.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping(value = "/schedule/weather")
    public String scheduleWeather(){

        return "layout/updating";
    }
    @GetMapping(value = "/schedule/scoreboard")

    public String scheduleScoreBoard( @RequestParam(name = "date" , defaultValue = "20240323") String date, Model model){

        List<Schedule> scheduleList = scheduleService.findByDate(date);
        model.addAttribute("scheduleList", scheduleList);

        return "schedule/scheduleScore";
    }

    @GetMapping (value = "/api/schedule")
    public String getSchedule(@RequestParam("date") String date, Model model) {
        System.out.println("data 값은?" + date);
        List<Schedule> schedule = scheduleService.findByDate(date);
        System.out.println("schedule 값은" + schedule);
        model.addAttribute("scheduleList", schedule);
        return "home";
    }
}
