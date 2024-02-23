package io.security.corespringsecurity.controller;


import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.board.Board;
import io.security.corespringsecurity.domain.entity.board.Category;
import io.security.corespringsecurity.domain.entity.board.Comment;
import io.security.corespringsecurity.domain.entity.board.Like;
import io.security.corespringsecurity.domain.entity.kbo.crawl.Team;
import io.security.corespringsecurity.domain.entity.kbo.Teams;
import io.security.corespringsecurity.domain.entity.profile.Notification;
import io.security.corespringsecurity.domain.entity.schedule.Schedule;
import io.security.corespringsecurity.repository.board.BoardRepository;
import io.security.corespringsecurity.repository.kbo.crawl.TeamRepository;
import io.security.corespringsecurity.service.board.BoardService;
import io.security.corespringsecurity.service.board.CategoryService;
import io.security.corespringsecurity.service.board.CommentService;
import io.security.corespringsecurity.service.board.LikeService;
import io.security.corespringsecurity.service.kbo.ClubService;
import io.security.corespringsecurity.service.kbo.ScheduleService;
import io.security.corespringsecurity.service.profile.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

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
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private BoardService boardService;

	@GetMapping(value="/")
	public String home(Model model, Principal principal, @RequestParam(name = "date" , defaultValue = "20240323") String date){

		Account account = null;
		if (principal instanceof UsernamePasswordAuthenticationToken) {
			account = (Account) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
		}
		model.addAttribute("account",account);

		if(account != null){
			int falseNotificationCount = notificationService.countFalseNotifications(account.getId());
			model.addAttribute("falseNotificationCounts",falseNotificationCount);

			// 알림 조회
			Long accountId = account.getId();
			List<Notification> notifications = notificationService.findByAccountId(accountId);
			model.addAttribute("notifications", notifications);
		}
		Pageable pageable = PageRequest.of(0, 5);

		List<String> categoryNames = Arrays.asList("GATHERING", "STORY");
		List<Category> categories = categoryService.findByCategoryNames(categoryNames);
		Page<Board> top5Boards = boardService.getListTop5(pageable, categories);

		Category categoryName = categoryService.findByCategoryName("BLOG");

		Page<Board> rumorTop5 = boardService.getListArticleTop5(pageable, categoryName);
		model.addAttribute("rumorTop5",rumorTop5);

		Category category = categoryService.findByCategoryName("NEWS");

		Page<Board> newsTop5 = boardService.getListArticleTop5(pageable, category);
		model.addAttribute("newsTop5",newsTop5);

		Category Acategory = categoryService.findByCategoryName("ARTICLE");

		Page<Board> articleTop5 = boardService.getListArticleTop5(pageable, Acategory);
		model.addAttribute("articleTop5",articleTop5);

		List<Team> team = teamRepository.findAll();
		List<Teams> clubs =  clubService.findAll();

		List<Schedule> findByScheduleAll = scheduleService.findAll();

		List<String> dates = findByScheduleAll.stream()
				.map(Schedule::getDate)
				.distinct()
				.collect(Collectors.toList());



		List<Schedule> scheduleList = scheduleService.findByDate(date);


		Set<Comment> commentList = new HashSet<>();
		List<String> teams = Arrays.asList("HH", "HT", "KT", "LG", "LT", "NC", "OB", "SK", "SS", "WO");

		model.addAttribute("teamImage", teams);
		model.addAttribute("comments", commentList);
		model.addAttribute("scheduleList",scheduleList);
		model.addAttribute("scheduleDate", dates);
		model.addAttribute("clubs", clubs);
		model.addAttribute("teams", team);
		model.addAttribute("communityTop5",top5Boards);
		return "home";
	}

	@PostMapping("/winning/player")
	@ResponseBody
	public String receiveName(@RequestParam("name") String name) {
		// 전달받은 이름을 사용하는 로직
		System.out.println("Received name: " + name);

		// 클라이언트에게 응답을 보냄. 예제에서는 단순한 문자열 응답
		return "Name received successfully";
	}
}
