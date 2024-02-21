package io.security.corespringsecurity.controller.kbo;

import io.security.corespringsecurity.domain.dto.kbo.crawl.PlayerDto;
import io.security.corespringsecurity.domain.entity.kbo.Players;
import io.security.corespringsecurity.domain.entity.kbo.crawl.Player;
import io.security.corespringsecurity.domain.entity.kbo.Teams;
import io.security.corespringsecurity.service.kbo.ClubService;
import io.security.corespringsecurity.service.kbo.PlayerService;
import io.security.corespringsecurity.service.kbo.PlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class PlayerContorller {

    @Autowired
    private PlayersService playersService;

    @Autowired
    private PlayerService playerService;
    @Autowired
    private ClubService clubService;

    @GetMapping("/player/search")
    public String playerSearch(Model model) {
        List<Players> playerList = playersService.findAll();
        model.addAttribute("player", playerList);
        return "player/playerSearch";
    }

    @GetMapping(value = "/player/search/sort")
    public String sortHitterRecords(Model model, @RequestParam(value = "name", defaultValue = "All") String name,
                                    @RequestParam(value = "team", defaultValue = "All") String team,
                                    @RequestParam(value = "position", defaultValue = "All") String position) {
        // TODO: hitterService에서 정렬된 데이터를 가져오는 메서드 호출
        System.out.println("name 값은?" + name);
        System.out.println("position 값은" + position);
        System.out.println("팀 이름은? " + team);
        // 모델에 정렬된 데이터 추가
        List<Players> sortedPaging = playersService.findByQuery(team, position, name);

        // 검색 결과의 총합 계산
        int totalResult = sortedPaging.size();
        System.out.println("sorted값은? " + sortedPaging);
        System.out.println("totalResults 값은? " + totalResult);
        model.addAttribute("player", sortedPaging);

        model.addAttribute("totalResults", totalResult); // Todo: 이부분은 작동을 안함. 타임리프에 값이 전달이 안된다. 이유 찾아보기.
        return "player/playerSearch";
    }

    @GetMapping(value = "/manager/player/create")
    public String playerForm(@ModelAttribute("playerForm") PlayerDto form, Model model) {
        List<Teams> teamsList = clubService.findAll();
        System.out.println("teamsList 값은?" + teamsList);
        model.addAttribute("teamslist",teamsList);

        return "player/createPlayerForm";
    }


    @PostMapping(value = "/manager/player/create")
    public String create(@RequestParam("teamId") Long teamId ,@Valid PlayerDto form, MultipartFile file, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            return "player/createPlayerForm";
        }

        String projectPath = System.getProperty("user.dir") + "//src//main//resources//static//images";
        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        Player player = new Player();
        Teams selectedTeam = clubService.findById(teamId).orElse(null);

        player.setFilepath("/images/" + fileName);
        player.setFilename(fileName);
        player.setName(form.getName());
        player.setBirthDate(form.getBirthDate());
        player.setHeight(form.getHeight());
        player.setWeight(form.getWeight());
        player.setAnnualSalary(form.getAnnualSalary());
        player.setSigningBonus(form.getSigningBonus());
        player.setCareerHistory(form.getCareerHistory());
        player.setPosition(form.getPosition());
        player.setDebutYear(form.getDebutYear());
        player.setJerseyNumber(form.getJerseyNumber());
        player.setDraftRank(form.getDraftRank());
        playerService.save(player);

        return "redirect:/";
    }

    @GetMapping("/manager/player/edit/{playerId}")
    public String showEditForm(@PathVariable("playerId") Long playerId, Model model) {
        Optional<Player> players = playerService.findById(playerId);

        Player player = players.orElse(new Player());
        model.addAttribute("players", player);
        return "player/editForm"; // 수정 폼의 Thymeleaf 템플릿 이름
    }

    @PostMapping("/manager/player/edit/{playerId}")
    public String handleEditForm(@PathVariable("playerId") Long playerId, @ModelAttribute Player updatedBoard) {
        playerService.editByPlayer(playerId, updatedBoard);
        return "redirect:/player/search";
    }
}
