package io.security.corespringsecurity.controller;


import io.security.corespringsecurity.domain.entity.kbo.TeamEntity;
import io.security.corespringsecurity.repository.kbo.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
	@Autowired
	private TeamRepository teamRepository;

	@GetMapping(value="/")
	public String home(Model model) throws Exception {

		List<TeamEntity> teamEntity = teamRepository.findAll();
		model.addAttribute("teams", teamEntity);
		return "home";
	}


}
