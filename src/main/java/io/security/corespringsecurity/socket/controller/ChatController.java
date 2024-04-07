package io.security.corespringsecurity.socket.controller;

import io.security.corespringsecurity.domain.dto.ChatRoomDto;
import io.security.corespringsecurity.domain.dto.board.BoardDto;
import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.board.Board;
import io.security.corespringsecurity.domain.entity.board.Category;
import io.security.corespringsecurity.domain.entity.profile.ImageEntity;
import io.security.corespringsecurity.service.board.ImageService;
import io.security.corespringsecurity.socket.WebSocketHandler;
import io.security.corespringsecurity.socket.entity.ChatRoom;
import io.security.corespringsecurity.socket.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.security.Principal;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final WebSocketHandler webSocketHandler;

    @Autowired
    private ImageService imageService;



    @RequestMapping("chat/chatList")
    public String chatList(Model model){
        List<ChatRoom> roomList = chatService.findAllRoom();
        System.out.println("확인" + roomList);

        model.addAttribute("roomList",roomList);
        return "chat/chatList";
    }

    @GetMapping(value = "/members/chat")
    public String boardForm(@ModelAttribute("chatForm") ChatRoomDto form, Model model) {
        List<ImageEntity> images = imageService.findAll();

        model.addAttribute("images", images);
        return "chat/createRoomForm";
    }

//    @PostMapping("members/chat/createRoom")  //방을 만들었으면 해당 방으로 가야지.
//    public String createRoom(Model model, @RequestParam String name, String username) {
//        ChatRoom room = chatService.createRoom(name);
//        model.addAttribute("room",room);
//        model.addAttribute("username",username);
//        return "chat/chatRoom";  //만든사람이 채팅방 1빠로 들어가게 됩니다
//    }

    @PostMapping(value = "/members/chat/createRoom")
    public String create(@Valid @ModelAttribute("chatForm") ChatRoomDto form, BindingResult result, Model model , @AuthenticationPrincipal Principal principal){

        if(result.hasErrors()) {
            return "chat/createRoomForm";
        }

        String uniqueID = UUID.randomUUID().toString();

        ChatRoom chatRoom = new ChatRoom();

        chatRoom.setRoomId(uniqueID);
        chatRoom.setName(form.getName());
        chatRoom.setKeyword(form.getKeyword());
        chatRoom.setTopic(form.getTopic()); // 스포츠 , 모임,
        System.out.println("확인" + form.getFilepath());
        chatRoom.setFilepath(form.getFilepath());
        chatRoom.setHeadcount(form.getHeadcount());
        chatRoom.setGender(form.getGender());
        chatRoom.setMinYear(form.getMinYear());
        chatRoom.setMaxYear(form.getMaxYear());

        chatService.create(chatRoom);

        webSocketHandler.getChatRoomSessions().putIfAbsent(uniqueID, ConcurrentHashMap.newKeySet());

        return "redirect:/chat/chatRoom/" + uniqueID;
    }

    @GetMapping("/chat/chatRoom/{roomId}")
    public String chatRoom(Model model, @PathVariable String roomId) {
        ChatRoom room = chatService.findRoomById(roomId);
        System.out.println("room 확인 " + room);

        model.addAttribute("room", room);
        return "chat/chatRoom";
    }
}