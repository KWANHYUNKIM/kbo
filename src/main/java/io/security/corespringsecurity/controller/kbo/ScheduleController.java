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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping(value = "/schedule/schedule")
    public String scheduleSchedule(  @RequestParam(name = "year", defaultValue = "2024") int year,
                                     @RequestParam(name = "month", defaultValue = "3") int month,
                                     Model model ){

        // 월의 시작 날짜와 끝 날짜를 계산
        LocalDate startOfMonth = LocalDate.of(year, month, 1);
        LocalDate endOfMonth = startOfMonth.withDayOfMonth(startOfMonth.lengthOfMonth());

        // 날짜 포맷을 'yyyyMMdd'로 설정
        String startDate = startOfMonth.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String endDate = endOfMonth.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        List<Schedule> scheduleList = scheduleService.findByDateRange(startDate, endDate);

        Map<String, Integer> rowspanMap = new HashMap<>();

        for (Schedule schedule : scheduleList) {
            String date = schedule.getDate();
            if (!rowspanMap.containsKey(date)) {
                rowspanMap.put(date, getRowspan(scheduleList, date));
            }
        }


        model.addAttribute("scheduleList",scheduleList);
        model.addAttribute("rowspanMap", rowspanMap);

        return "schedule/scheduleSchedule";
    }



    @GetMapping(value = "/schedule/weather")
    public String scheduleWeather(){

        return "layout/updating";
    }
    @GetMapping(value = "/schedule/scoreboard")

    public String scheduleScoreBoard(@RequestParam(name = "date" , defaultValue = "20230401") String date, Model model){

        // 월의 시작 날짜와 끝 날짜를 계산

        List<Schedule> scheduleList = scheduleService.findByDate(date);
        model.addAttribute("scheduleList", scheduleList);

        return "schedule/scheduleScore";
    }

    @GetMapping (value = "/api/schedule")
    public String getSchedule(@RequestParam("date") String date, Model model) {
        List<Schedule> schedule = scheduleService.findByDate(date);
        model.addAttribute("scheduleList", schedule);
        return "home";
    }

    public static int getRowspan(List<Schedule> scheduleList, String date) {
        return (int) scheduleList.stream().filter(schedule -> schedule.getDate().equals(date)).count();
    }

}
