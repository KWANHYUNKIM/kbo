package io.security.corespringsecurity.controller.games;

import com.google.common.base.Optional;
import io.security.corespringsecurity.domain.dto.board.CommentDto;
import io.security.corespringsecurity.domain.dto.kbo.games.GameCommentDto;
import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.board.Board;
import io.security.corespringsecurity.domain.entity.board.Comment;
import io.security.corespringsecurity.domain.entity.kbo.games.Entrance;
import io.security.corespringsecurity.domain.entity.kbo.games.EntrancePlayer;
import io.security.corespringsecurity.domain.entity.kbo.games.GameComment;
import io.security.corespringsecurity.domain.entity.profile.Notification;
import io.security.corespringsecurity.domain.entity.schedule.Schedule;
import io.security.corespringsecurity.service.kbo.ScheduleService;
import io.security.corespringsecurity.service.kbo.games.EntrancePlayerService;
import io.security.corespringsecurity.service.kbo.games.GameCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Controller
public class gameController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private GameCommentService gameCommentService;

    @Autowired
    private EntrancePlayerService entrancePlayerService;


    @GetMapping("/game/{scheduleId}/reply")
    public String gamePage(@PathVariable String scheduleId, Model model,
                           @ModelAttribute("commentForm") GameCommentDto form) {

        String date = scheduleId.substring(0,8);
        String[] teams = {"LG", "KT", "SSG", "NC", "두산", "KIA", "롯데", "삼성", "한화", "키움"};
        HashSet<String> matchingTeams = new HashSet<>();

        for (String team : teams) {
            if (scheduleId.contains(team)) {
                matchingTeams.add(team);
            }
        }

        String[] teamArray = matchingTeams.toArray(new String[0]);
        String awayTeam = teamArray[0];
        String homeTeam = teamArray.length > 1 ? teamArray[1] : null;


        // 그 날짜의 야구 하는 팀 스케줄 표을 표현 가능
        List<Schedule> scheduleList = scheduleService.findByDate(date);
        List<Schedule> scheduleDetails = scheduleService.findByDateAndTeam(date, awayTeam, homeTeam);
        if(scheduleDetails.isEmpty()){
            awayTeam = teamArray[1];
            homeTeam = teamArray[0];

            scheduleDetails = scheduleService.findByDateAndTeam(date, awayTeam, homeTeam);
        }

        if(!scheduleDetails.isEmpty()){
            Schedule scheduleDetail = scheduleDetails.get(0);
            List<Entrance> entrances = scheduleService.findByEntrance(scheduleDetail);

            if (!entrances.isEmpty()) {
                List<EntrancePlayer> entrancePlayer = entrancePlayerService.findByPlayer(entrances.get(0).getId());
                model.addAttribute("entrancePlayer",entrancePlayer);
            }

        }

        model.addAttribute("scheduleList",scheduleList);
        model.addAttribute("scheduleDetails",scheduleDetails);

        return "/game/gameDetails";
    }

//    @PostMapping("/members/game/{scheduleId}/comment")
//    public String createForm(Principal principal, @PathVariable Long scheduleId,
//                             @ModelAttribute GameCommentDto form) {
//        Account account = null;
//        GameComment comment = new GameComment();
//
//        if (principal instanceof UsernamePasswordAuthenticationToken) {
//            account = (Account) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
//            comment.setAccount(account);
//        }
//
//        Schedule schedule = scheduleService.findByComment(scheduleId);  // 해당 boardId에 대한 게시물을 가져옴
//
//
//        comment.setComment(form.getComment());
//        comment.setSchedule(schedule);  // Comment 엔티티의 board 속성에 Board 객체를 설정
//
//
//
//        // 여기서 boardId를 사용하여 해당 게시물을 식별하여 댓글을 저장합니다.
//        gameCommentService.save(comment);
//
//
//
//        return "redirect:";
//    }


    @PostMapping("/members/game/{scheduleId}/comment")
    public ResponseEntity<String> createForm(Principal principal, @PathVariable Long scheduleId, @ModelAttribute GameCommentDto form) {
        Account account = null;
        GameComment comment = new GameComment();
        HttpStatus status = null;

        if (principal instanceof UsernamePasswordAuthenticationToken) {
            account = (Account) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
            comment.setAccount(account);
        }

        Schedule schedule = scheduleService.findByComment(scheduleId);  // 해당 boardId에 대한 게시물을 가져옴

        if (schedule != null) {
            comment.setComment(form.getComment());
            comment.setSchedule(schedule);  // Comment 엔티티의 board 속성에 Board 객체를 설정

            // 여기서 boardId를 사용하여 해당 게시물을 식별하여 댓글을 저장합니다.
            gameCommentService.save(comment);

            status = HttpStatus.CREATED; // 댓글이 성공적으로 생성됨을 나타내는 HttpStatus 코드
        } else {
            status = HttpStatus.NOT_FOUND; // 요청된 게시물이 없음을 나타내는 HttpStatus 코드
        }

        return ResponseEntity.status(status).body(null);
    }

}



