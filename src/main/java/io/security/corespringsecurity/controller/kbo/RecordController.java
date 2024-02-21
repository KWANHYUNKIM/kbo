package io.security.corespringsecurity.controller.kbo;

import io.security.corespringsecurity.domain.entity.kbo.*;
import io.security.corespringsecurity.domain.entity.kbo.crawl.Player;
import io.security.corespringsecurity.domain.entity.kbo.crawl.TeamYear;
import io.security.corespringsecurity.service.kbo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class RecordController {


    @Autowired
    private TeamYearService teamYearService;
    @Autowired
    private PlayerRecordService playerRecordService;
    @Autowired
    private PlayersService playersService;

    @GetMapping(value = "/record/all")
    public String recordPlayer(){
        // Todo : 해야함.

        return "layout/updating";
    }

    @GetMapping(value ="/record/pitcher/all")
    public String recordPitcher(Model model ,@RequestParam(value="page", defaultValue="0") int page){
//        Page<Pitcher> paging = this.pitcherService.getList(page);
//        model.addAttribute("paging", paging);

        return "kbo/recordPitcher";
    }

    @GetMapping(value ="/record/hitter/all")
    public String recordHitter(Model model){
//        List<Hitter> paging = this.hitterService.getList();
//        model.addAttribute("paging", paging);

        return "kbo/recordHitter";
    }
    @GetMapping(value = "/record/hitter/sort")
    public String sortHitterRecords(Model model, @RequestParam(value = "column", defaultValue = "All") String columnName,
                                    @RequestParam(value = "team", defaultValue = "All") String team,
                                    @RequestParam(value = "year", defaultValue = "All") String year) {
        // TODO: hitterService에서 정렬된 데이터를 가져오는 메서드 호출
//        System.out.println("team 값은?" + year);
//        List<Hitter> sortedPaging = this.hitterService.getSortedHitterList(columnName,team);
//        model.addAttribute("paging", sortedPaging);

        // "kbo/recordHitter" 템플릿으로 이동
        return "kbo/recordHitter";
    }

    @GetMapping(value = "/record/hitter/teamRanking")
    public String teamDataSort(Model model, @RequestParam(value = "team") String team) {
//        System.out.println("column 값은?" +team);
//        List<Hitter> sortedPaging = this.hitterService.findByTeam(team);
//        System.out.println("값은? +" + sortedPaging);
        // 모델에 정렬된 데이터 추가
//        model.addAttribute("hitters", sortedPaging);

        // "kbo/recordHitter" 템플릿으로 이동
        return "teamRankings/teamHitter";
    }

    @GetMapping(value ="/record/teamRanking")
    public String recordTeamRank (Model model, @RequestParam(name = "years", defaultValue = "2023") String year){
        List<TeamYear> teamYears = teamYearService.findAll(year);
        List<TeamYear> ranking = teamYearService.findAll();
        System.out.println("teamYear 값은? " + teamYears);
        model.addAttribute("teamyears", teamYears);
        model.addAttribute("rankings",ranking);
        return "kbo/recordTeamRank";
    }

    @GetMapping(value ="/record/ranking/top5")
    public String recordPlayerTop5(Model model){
//        Pageable pageable = PageRequest.of(0, 10);
//        List<Hitter> atHitTop5 = hitterService.findByHitTop5(pageable);
//        List<Hitter> atHomeRunTop5 = hitterService.findByHomrunTop5(pageable);
//        // Todo : 투수편 만들기
//        model.addAttribute("atHitTop5s",atHitTop5);
//        model.addAttribute("atHomeRunTop5s",atHomeRunTop5);
        return "layout/updating";
    }

    @GetMapping(value = "/record/player/hitterDetail/{playerId}")
    public String recordPlayerDetails(Model model, @PathVariable("playerId") Long playerId){

        List<Players> player = playersService.findById(playerId);
        List<PlayerRecord> playerRecords = playerRecordService.findByIdList(player);

        Pageable pageable = PageRequest.of(0, 10);
        List<PlayerRecord> FindRecent10Games = playerRecordService.findRecent10Games(player,pageable);


        Object[] totalValues = new Object[16]; // 필드가 총 14개이므로 배열의 크기를 14로 설정합니다.
        List<Object[]> totalRecordsList = new ArrayList<>();
        String team = player.get(0).getTeams().getTeamName();

        int totalPA = 0;
        int totalAB = 0;
        int totalR = 0;
        int totalH = 0;
        int totalDoubles = 0;
        int totalTriples = 0;
        int totalHR = 0;
        int totalRBI = 0;
        int totalSB = 0;
        int totalCS = 0;
        int totalBB = 0;
        int totalHBP = 0;
        int totalSO = 0;
        int totalGDP = 0;
        double avg2 = 0;

        for(int i = 0 ; i < playerRecords.size(); i++){
            if(playerRecords.get(i).getPlayerRecordHitter() != null){
                System.out.println("player 값은?" + playerRecords.get(i).getPlayerRecordHitter());

                totalPA += playerRecords.get(i).getPlayerRecordHitter().getPa();
                totalAB +=playerRecords.get(i).getPlayerRecordHitter().getAb();
                totalR += playerRecords.get(i).getPlayerRecordHitter().getR();
                totalH += playerRecords.get(i).getPlayerRecordHitter().getH();
                totalDoubles += playerRecords.get(i).getPlayerRecordHitter().getDoubles();
                totalTriples += playerRecords.get(i).getPlayerRecordHitter().getTriples();
                totalHR += playerRecords.get(i).getPlayerRecordHitter().getHr();
                totalRBI += playerRecords.get(i).getPlayerRecordHitter().getRbi();
                totalSB += playerRecords.get(i).getPlayerRecordHitter().getSb();
                totalCS += playerRecords.get(i).getPlayerRecordHitter().getCs();
                totalBB += playerRecords.get(i).getPlayerRecordHitter().getBb();
                totalHBP += playerRecords.get(i).getPlayerRecordHitter().getHbp();
                totalSO += playerRecords.get(i).getPlayerRecordHitter().getSo();
                totalGDP += playerRecords.get(i).getPlayerRecordHitter().getGdp();
                totalGDP += playerRecords.get(i).getPlayerRecordHitter().getGdp();
                avg2 = playerRecords.get(i).getPlayerRecordHitter().getAvg2();
                totalValues[0] = totalPA;
                totalValues[1] = totalAB;
                totalValues[2] = totalR;
                totalValues[3] = totalH;
                totalValues[4] = totalDoubles;
                totalValues[5] = totalTriples;
                totalValues[6] = totalHR;
                totalValues[7] = totalRBI;
                totalValues[8] = totalSB;
                totalValues[9] = totalCS;
                totalValues[10] = totalBB;
                totalValues[11] = totalHBP;
                totalValues[12] = totalSO;
                totalValues[13] = totalGDP;
                totalValues[14] = team;
                totalValues[15] = avg2;
            }
            if(playerRecords.get(i).getPlayerRecordHitter() != null && i == playerRecords.size() - 1){
               totalValues[15] =  playerRecords.get(i).getPlayerRecordHitter().getAvg2();
            }

        }
        totalRecordsList.add(totalValues);


        List<PlayerRecord> detailsHitter = playerRecordService.findByDetailsHitter(player);

        model.addAttribute("detailsHitterList",detailsHitter);
        model.addAttribute("totalRecordsList",totalRecordsList);
        model.addAttribute("findRecent10Games",FindRecent10Games);
        model.addAttribute("players", player);
        model.addAttribute("records", playerRecords);

        return "kbo/recordHitterDetail";

    }

    @GetMapping(value = "/record/player/pitcherDetail/{playerId}")
    public String recordPitcherDetails(Model model, @PathVariable("playerId") Long playerId){
        List<Players> player = playersService.findById(playerId);
        List<PlayerRecord> playerRecords = playerRecordService.findByIdList(player);

         model.addAttribute("records",playerRecords);
        model.addAttribute("players", player);

        return "kbo/recordPitcherDetail";
    }

}
