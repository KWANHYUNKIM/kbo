package io.security.corespringsecurity.socket.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.security.corespringsecurity.repository.ChatRepository;
import io.security.corespringsecurity.socket.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    private Map<String, ChatRoom> chatRooms;

    public void create(ChatRoom chatRoom) {
        chatRepository.save(chatRoom);
    }

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom() {
        return chatRepository.findAll();
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRepository.findByRoomId(roomId);
    }

}
