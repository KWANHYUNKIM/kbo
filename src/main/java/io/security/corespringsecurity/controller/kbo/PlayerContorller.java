package io.security.corespringsecurity.controller.kbo;

import io.security.corespringsecurity.domain.entity.kbo.Player;
import io.security.corespringsecurity.service.kbo.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PlayerContorller {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/player/search")
    public String playerSearch(Model model){
        List<Player> playerList = playerService.findAll();
        model.addAttribute("player", playerList);
        return "player/playerSearch";
    }

    @GetMapping(value = "/player/search/sort")
    public String sortHitterRecords(Model model, @RequestParam(value = "name", defaultValue = "All") String name,
                                    @RequestParam(value = "team", defaultValue = "All") String team,
                                    @RequestParam(value = "position", defaultValue = "All") String position) {
        // TODO: hitterService에서 정렬된 데이터를 가져오는 메서드 호출
        System.out.println("name 값은?" +name);
        // 모델에 정렬된 데이터 추가
        List<Player> sortedPaging = playerService.findByQuery(team,position,name);

        // 검색 결과의 총합 계산
        int totalResult = sortedPaging.size();
        System.out.println("totalResults 값은? " + totalResult);

        model.addAttribute("player", sortedPaging);
        model.addAttribute("totalResults", totalResult); // Todo: 이부분은 작동을 안함. 타임리프에 값이 전달이 안된다. 이유 찾아보기.
        return "player/playerSearch";
    }

}
