package io.security.corespringsecurity.controller;


import io.security.corespringsecurity.domain.entity.board.Board;
import io.security.corespringsecurity.domain.entity.kbo.crawl.Team;
import io.security.corespringsecurity.domain.entity.kbo.Teams;
import io.security.corespringsecurity.domain.entity.schedule.Schedule;
import io.security.corespringsecurity.repository.board.BoardRepository;
import io.security.corespringsecurity.repository.kbo.crawl.TeamRepository;
import io.security.corespringsecurity.service.kbo.ClubService;
import io.security.corespringsecurity.service.kbo.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private ScheduleService scheduleService;
	@Autowired
	private ClubService clubService;
	@GetMapping(value="/")
	public String home(Model model) throws Exception {
		Pageable pageable = PageRequest.of(0, 5);
		List<Board> board = boardRepository.findTop5ByOrderByTagCountDesc(pageable);
		List<Team> team = teamRepository.findAll();
		List<Teams> clubs =  clubService.findAll();
		List<Schedule> schedules = scheduleService.findAll();

		model.addAttribute("scheduleLists", schedules);
		model.addAttribute("clubs", clubs);
		model.addAttribute("teams", team);
		model.addAttribute("boards",board);
		return "home";
	}


}
