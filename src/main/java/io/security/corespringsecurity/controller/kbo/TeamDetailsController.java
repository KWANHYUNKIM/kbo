package io.security.corespringsecurity.controller.kbo;

import io.security.corespringsecurity.domain.entity.kbo.Hitter;
import io.security.corespringsecurity.domain.entity.kbo.Pitcher;
import io.security.corespringsecurity.service.kbo.HitterService;
import io.security.corespringsecurity.service.kbo.PitcherService;
import io.security.corespringsecurity.service.kbo.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TeamDetailsController {
    @Autowired
    private HitterService hitterService;
    @Autowired
    private PitcherService pitcherService;
    @Autowired
    private TeamService teamService;
    @GetMapping(value = "/teamRankings/{team}/")
    public String detailList(Model model, @PathVariable("team") String team ){


        List<Pitcher> pitchers = pitcherService.findByTeam(team);
//        List<Team> teams = teamService.findByTeam(team);

//        System.out.println("hitter is" + hitters + "pitchers is" + pitchers + "teams" + teams);
        model.addAttribute("pitchers", pitchers);
//        model.addAttribute("teams", teams);

        return "/teamRankings/teamBoardList";

    }
//    @GetMapping(value ="/teamRankings/{team}/hitter/all")
//    public String teamHitterDetailsList(Model model,@PathVariable("team") String team){
//        List<Hitter> hitters = hitterService.findByTeam(team);
//        model.addAttribute("hitters",hitters);
//        return "/teamRankings/"
//    }
}
