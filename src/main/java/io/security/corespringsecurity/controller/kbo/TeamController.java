package io.security.corespringsecurity.controller.kbo;


import io.security.corespringsecurity.domain.dto.kbo.TeamDto;
import io.security.corespringsecurity.domain.entity.kbo.TeamEntity;
import io.security.corespringsecurity.repository.kbo.TeamRepository;
import io.security.corespringsecurity.service.kbo.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class TeamController {
//
//    @Autowired
//    private TeamRepository teamRepository;
//
//    @GetMapping("/kbo/team/all")
//    public String list(Model model) {
//        List<TeamEntity> teamEntity = teamRepository.findAll();
//        model.addAttribute("teams",teamEntity);
//        return "kbo/teamBoardList";
//    }
}
