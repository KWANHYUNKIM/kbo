package io.security.corespringsecurity.controller;


import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.board.Board;
import io.security.corespringsecurity.domain.entity.kbo.Team;
import io.security.corespringsecurity.repository.board.BoardRepository;
import io.security.corespringsecurity.repository.kbo.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {
	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private BoardRepository boardRepository;

	@GetMapping(value="/")
	public String home(Model model) throws Exception {
		Pageable pageable = PageRequest.of(0, 5);
		List<Board> board = boardRepository.findTop5ByOrderByTagCountDesc(pageable);
		List<Team> team = teamRepository.findAll();

		model.addAttribute("teams", team);
		model.addAttribute("boards",board);
		return "home";
	}


}
