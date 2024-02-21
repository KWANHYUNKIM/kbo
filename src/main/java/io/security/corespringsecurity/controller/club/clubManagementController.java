package io.security.corespringsecurity.controller.club;

import io.security.corespringsecurity.domain.dto.kbo.games.EntranceDto;
import io.security.corespringsecurity.domain.dto.kbo.games.ScheduleDto;
import io.security.corespringsecurity.domain.dto.kbo.crawl.TeamDto;
import io.security.corespringsecurity.domain.dto.kbo.games.TeamsDto;
import io.security.corespringsecurity.domain.entity.auth.Role;
import io.security.corespringsecurity.domain.entity.kbo.PlayerRecord;
import io.security.corespringsecurity.domain.entity.kbo.Players;
import io.security.corespringsecurity.domain.entity.kbo.Teams;
import io.security.corespringsecurity.domain.entity.kbo.games.Entrance;
import io.security.corespringsecurity.domain.entity.schedule.Location;
import io.security.corespringsecurity.domain.entity.schedule.Schedule;
import io.security.corespringsecurity.service.kbo.ClubService;
import io.security.corespringsecurity.service.kbo.LocationService;
import io.security.corespringsecurity.service.kbo.PlayersService;
import io.security.corespringsecurity.service.kbo.ScheduleService;
import io.security.corespringsecurity.service.kbo.games.EntranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class clubManagementController {

    @Autowired
    ClubService clubService;

    @Autowired
    LocationService locationService;

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    PlayersService playersService;

    @Autowired
    EntranceService entranceService;

    @GetMapping(value = "/club")
    public String clubManagement(Model model, Principal principal) throws Exception{
        String auth = getCurrentUserRole();

        System.out.println("권한값은? " + auth);

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

//        Players player = playersService.findById(playerId);
//        List<PlayerRecord> playerRecords = player.getPlayerRecord();


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
    @GetMapping("admin/club/entrance/posted/{entranceId}/home")
    public String postHomeEntrance(Model model, @PathVariable String entranceId , @ModelAttribute("entranceForm") EntranceDto form){

        String date = entranceId.substring(0,8);
        String[] teams = {"LG", "KT", "SSG", "NC", "두산", "KIA", "롯데", "삼성", "한화", "키움"};
        HashSet<String> matchingTeams = new HashSet<>();

        for (String team : teams) {
            if (entranceId.contains(team)) {
                matchingTeams.add(team);
            }
        }

        String[] teamArray = matchingTeams.toArray(new String[0]);
        String awayTeam = teamArray[0];
        String homeTeam = teamArray.length > 1 ? teamArray[1] : null;

        // 그 날짜의 야구 하는 팀 스케줄 표을 표현 가능
        List<Schedule> scheduleDetails = scheduleService.findByDateAndTeam(date, awayTeam, homeTeam);
        model.addAttribute("entranceId",entranceId);
        model.addAttribute("scheduleDetails",scheduleDetails);

        return "club/postEntrance";
    }

    @GetMapping("admin/club/entrance/posted/{entranceId}/away")
    public String postAwayEntrance(Model model, @PathVariable String entranceId , @ModelAttribute("entranceForm") EntranceDto form){
        System.out.println("entranceId 값은? " + entranceId);

        String date = entranceId.substring(0,8);
        String[] teams = {"LG", "KT", "SSG", "NC", "두산", "KIA", "롯데", "삼성", "한화", "키움"};
        HashSet<String> matchingTeams = new HashSet<>();

        for (String team : teams) {
            if (entranceId.contains(team)) {
                matchingTeams.add(team);
            }
        }

        String[] teamArray = matchingTeams.toArray(new String[0]);
        String awayTeam = teamArray[0];
        String homeTeam = teamArray.length > 1 ? teamArray[1] : null;

        // 그 날짜의 야구 하는 팀 스케줄 표을 표현 가능
        List<Schedule> scheduleDetails = scheduleService.findByDateAndTeam(date, awayTeam, homeTeam);
        model.addAttribute("entranceId",entranceId);
        model.addAttribute("scheduleDetails",scheduleDetails);

        return "club/postAwayEntrance";
    }

    @PostMapping("/admin/club/entrance/createdEntrance")
    public String createEntrance(@ModelAttribute("entranceForm") EntranceDto form) {

        Entrance entrance = new Entrance();
//        System.out.println("entryCandidates 값?" + entryCandidates);
        entrance.setTeams(form.getTeams());
        entrance.setSchedule(form.getSchedule());
        entrance.setGameNumber(form.getGameNumber());

//         entryCandidates를 이용하여 Players를 찾아내어 설정
//        List<Players> selectedPlayers = playersService.findByIdIn(entryCandidates);
//        entrance.setPlayer(selectedPlayers);

        entranceService.save(entrance);

        return "club/postEntrance";
    }


    @PostMapping("/admin/club/deleteSelectedSchedules")
    @ResponseBody
    public Map<String, String> deleteSelectedSchedules(@RequestBody Map<String, List<Long>> requestBody) {
        Map<String, String> response = new HashMap<>();

        List<Long> scheduleIds = requestBody.get("scheduleIds");
        if (scheduleIds != null && !scheduleIds.isEmpty()) {
            scheduleIds.forEach(scheduleId -> scheduleService.deleteSchedule(scheduleId));
            response.put("status", "success");
            response.put("message", "Selected schedules deleted successfully.");
        } else {
            response.put("status", "error");
            response.put("message", "No schedule selected for deletion.");
        }

        return response;
    }


    @GetMapping("/admin/club/postedClub")
    public String showClubDetails(Model model){
        List<Teams> teamsList = clubService.findAll();
        System.out.println("teamList value is " + teamsList);
        model.addAttribute("teamsList",teamsList);
        return "club/clubDetails";
    }

    // 현재 사용자의 권한을 가져오는 메서드
    private String getCurrentUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getAuthorities().iterator().next().getAuthority();
        }

        return null;
    }
}
