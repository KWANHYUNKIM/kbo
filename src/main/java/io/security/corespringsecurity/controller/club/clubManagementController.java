package io.security.corespringsecurity.controller.club;

import io.security.corespringsecurity.domain.dto.kbo.games.ScheduleDto;
import io.security.corespringsecurity.domain.dto.kbo.crawl.TeamDto;
import io.security.corespringsecurity.domain.dto.kbo.games.TeamsDto;
import io.security.corespringsecurity.domain.entity.kbo.Players;
import io.security.corespringsecurity.domain.entity.kbo.Teams;
import io.security.corespringsecurity.domain.entity.schedule.Location;
import io.security.corespringsecurity.domain.entity.schedule.Schedule;
import io.security.corespringsecurity.service.kbo.ClubService;
import io.security.corespringsecurity.service.kbo.LocationService;
import io.security.corespringsecurity.service.kbo.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class clubManagementController {

    @Autowired
    ClubService clubService;

    @Autowired
    LocationService locationService;

    @Autowired
    ScheduleService scheduleService;


    @GetMapping(value = "/club")
    public String clubManagement() throws Exception{

        return "club/home";
    }

    @GetMapping(value = "/admin/club/createdClub")
    public String clubForm(@ModelAttribute("clubForm") TeamsDto form) throws Exception{

        return "club/createClubForm";

    }
    @PostMapping(value = "/admin/club/createdClub")
    public String create(@Valid TeamDto form, MultipartFile file) throws Exception{

        String projectPath = System.getProperty("user.dir") + "//src//main//resources//static//images";

        /*식별자 . 랜덤으로 이름 만들어줌*/
        UUID uuid = UUID.randomUUID();

        /*랜덤식별자_원래파일이름 = 저장될 파일이름 지정*/
        String fileName = uuid + "_" + file.getOriginalFilename();

        /*빈 껍데기 생성*/
        /*File을 생성할건데, 이름은 "name" 으로할거고, projectPath 라는 경로에 담긴다는 뜻*/
        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        Teams teams = new Teams();

        teams.setTeamName(form.getTeamName());
        teams.setFilename(fileName);
        teams.setFilepath("/images/" + fileName);

        clubService.createClub(teams);
        return "redirect:/club";
    }

    @GetMapping("/teams/{teamName}")
    public String getTeamDetails(@PathVariable String teamName, Model model) {
        System.out.println("teamName= " + teamName);

        Teams team = clubService.findByTeamName(teamName);
//        String players = team.getPlayers().toString();
//        System.out.println("players 값은?" + players);
//        System.out.println("teams 값은? " + team);
        if (team != null) {
            // 구단에 속한 선수 목록을 가져오기
            List<Players> playersList = team.getPlayers();

            // 선수 이름 목록을 추출하여 문자열로 변환
            String players = playersList.stream()
                    .map(Players::getName)
                    .collect(Collectors.joining(", "));

            // 모델에 구단과 선수 목록 추가
            model.addAttribute("team", team);
            model.addAttribute("players", players);

            // 뷰의 이름 반환
            return "club/clubDetails";
        } else {
            // 구단을 찾지 못한 경우 에러 처리 또는 다른 로직 수행
            return "/error"; // 예시로 에러 페이지로 리다이렉트
        }
    }

    @GetMapping("/admin/club/createdSchedule")
    public String createdSchedule(@ModelAttribute("scheduleForm") ScheduleDto form
                                  ,Model model){
        List<Teams> teamsList = clubService.findAll();
        List<Location> locationList = locationService.findAll();
        model.addAttribute("teams",teamsList);
        model.addAttribute("locations",locationList);
        return "club/createdSchedule";

    }

    @PostMapping("/admin/club/createdSchedule")
    public String getSchedule(@ModelAttribute("scheduleForm") ScheduleDto form){

        Schedule schedule = new Schedule();

        schedule.setDate(form.getDate());
        schedule.setTime(form.getTime());
        schedule.setLocation(form.getLocationName());
        schedule.setHomeTeam(form.getHomeTeam());
        schedule.setAwayTeam(form.getAwayTeam());

        scheduleService.save(schedule);

        return "redirect:/club";

    }

    @GetMapping("/admin/club/postedSchedule")
    public String postSchedule(Model model){
        List<Schedule> scheduleList = scheduleService.findAll();
        model.addAttribute("scheduleLists",scheduleList);
        return "club/showSchedule";
    }

    @GetMapping("/admin/club/postedClub")
    public String showClubDetails(Model model){
        List<Teams> teamsList = clubService.findAll();
        System.out.println("teamList value is " + teamsList);
        model.addAttribute("teamsList",teamsList);
        return "club/clubDetails";
    }
}
