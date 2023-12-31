package io.security.corespringsecurity.controller.kbo;

import io.security.corespringsecurity.domain.entity.kbo.Hitter;
import io.security.corespringsecurity.domain.entity.kbo.Pitcher;
import io.security.corespringsecurity.domain.entity.kbo.TeamYear;
import io.security.corespringsecurity.repository.kbo.TeamYearRepository;
import io.security.corespringsecurity.service.kbo.HitterService;
import io.security.corespringsecurity.service.kbo.PitcherService;
import io.security.corespringsecurity.service.kbo.TeamYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RecordController {

    @Autowired
    private HitterService hitterService;
    @Autowired
    private PitcherService pitcherService;
    @Autowired
    private TeamYearService teamYearService;

    @GetMapping(value = "/record/all")
    public String recordPlayer(){

        return "kbo/record";
    }

    @GetMapping(value ="/record/pitcher/all")
    public String recordPitcher(Model model ,@RequestParam(value="page", defaultValue="0") int page){
        Page<Pitcher> paging = this.pitcherService.getList(page);
        model.addAttribute("paging", paging);

        return "kbo/recordPitcher";
    }

    @GetMapping(value ="/record/hitter/all")
    public String recordHitter(Model model){
        List<Hitter> paging = this.hitterService.getList();
        model.addAttribute("paging", paging);

        return "kbo/recordHitter";
    }
    @GetMapping(value = "/record/hitter/sort")
    public String sortHitterRecords(Model model, @RequestParam(value = "column", defaultValue = "All") String columnName,
                                    @RequestParam(value = "team", defaultValue = "All") String team,
                                    @RequestParam(value = "year", defaultValue = "All") String year) {
        // TODO: hitterService에서 정렬된 데이터를 가져오는 메서드 호출
//        System.out.println("column 값은?" +columnName);
//        System.out.println("team 값은?" + team);
        System.out.println("team 값은?" + year);
        List<Hitter> sortedPaging = this.hitterService.getSortedHitterList(columnName,team);
//        System.out.println("값은? +" + sortedPaging);
        // 모델에 정렬된 데이터 추가
        model.addAttribute("paging", sortedPaging);

        // "kbo/recordHitter" 템플릿으로 이동
        return "kbo/recordHitter";
    }

    @GetMapping(value = "/record/hitter/teamRanking")
    public String teamDataSort(Model model, @RequestParam(value = "team") String team) {
        System.out.println("column 값은?" +team);
        List<Hitter> sortedPaging = this.hitterService.findByTeam(team);
        System.out.println("값은? +" + sortedPaging);
        // 모델에 정렬된 데이터 추가
        model.addAttribute("hitters", sortedPaging);

        // "kbo/recordHitter" 템플릿으로 이동
        return "teamRankings/teamHitter";
    }

    @GetMapping(value ="/record/teamRanking")
    public String recordTeamRank (Model model, @RequestParam(name = "years", defaultValue = "2023") String year){
        List<TeamYear> teamYears = teamYearService.findAll(year);
        List<TeamYear> ranking = teamYearService.findAll();
        model.addAttribute("teamyears", teamYears);
        System.out.println("ranking 값은?" + ranking);
        model.addAttribute("rankings",ranking);
        return "kbo/recordTeamRank";
    }

    @GetMapping(value ="/record/ranking/top5")
    public String recordPlayerTop5(Model model){

        return "kbo/recordPlayerTop5";
    }

}
