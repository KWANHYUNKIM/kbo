package io.security.corespringsecurity.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.security.corespringsecurity.socket.entity.ChatMessage;
import io.security.corespringsecurity.socket.entity.ChatRoom;
import io.security.corespringsecurity.socket.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatService chatService;



    private final Map<String, Set<WebSocketSession>> chatRoomSessions = new ConcurrentHashMap<>();

    public Map<String, Set<WebSocketSession>> getChatRoomSessions() {
        return chatRoomSessions;
    }

    //  WebSocket 연결이 성립될 때 호출되며, 여기서 각 세션을 적절한 채팅방에 할당하고 있습니다.
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String roomId = getRoomIdFromSession(session);
        if (roomId == null) {
            log.error("Room ID is null for session: {}", session);
            return; // 룸 ID가 null인 경우, 추가 처리를 중단합니다.
        }
        log.info("Session connected with Room ID: {}", roomId); // 로그에 룸 ID 출력
        chatRoomSessions.computeIfAbsent(roomId, k -> new CopyOnWriteArraySet<>()).add(session);
        log.info("Session added to Room: {}. Current sessions: {}", roomId, chatRoomSessions.get(roomId).size()); // 세션 추가 확인 로깅
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String roomId = getRoomIdFromSession(session);
        Set<WebSocketSession> sessions = chatRoomSessions.get(roomId);
        if (sessions != null) {
            sessions.remove(session);
            if (sessions.isEmpty()) {
                chatRoomSessions.remove(roomId);
            }
        }
    }


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
        String roomId = chatMessage.getRoomId();
        Set<WebSocketSession> sessions = chatRoomSessions.getOrDefault(roomId, new CopyOnWriteArraySet<>());
        log.info("Received payload: {}", payload);
        log.info("Received message: {}", chatMessage.getMessage());
        log.info("Room ID: {}", roomId);
        log.info("Sessions in room: {}", sessions.size());
        log.info("Received chatMessage.getType: {}" , chatMessage.getType());

        switch (chatMessage.getType()) {
            case ENTER:
                // 방에 입장할 때는 이미 afterConnectionEstablished에서 세션을 추가했으므로 별도의 추가 작업 필요 없음
                chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");
                sendToEachSocket(sessions, new TextMessage(objectMapper.writeValueAsString(chatMessage)));
                break;
            case QUIT:
                // 방에서 퇴장할 때는 세션을 제거
                sessions.remove(session);
                chatMessage.setMessage(chatMessage.getSender() + "님이 퇴장했습니다.");
                sendToEachSocket(sessions, new TextMessage(objectMapper.writeValueAsString(chatMessage)));
                break;
            case TALK:
                // 일반 대화 메시지 처리
                log.info("Received sessions : {}" , sessions);
                sendToEachSocket(sessions, message); // 모든 세션에 메시지를 보내는지 확인
                break;
        }
    }

    private void sendToEachSocket(Set<WebSocketSession> sessions, TextMessage message) {
        sessions.parallelStream().forEach(roomSession -> {
            try {
                roomSession.sendMessage(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    private String getRoomIdFromSession(WebSocketSession session) {
        URI uri = session.getUri();
        System.out.println("uri 값은?" + uri);
        if (uri != null) {
            String path = uri.getPath();
            String[] segments = path.split("/");
            // URL 형태에 따라 적절한 세그먼트 인덱스를 선택해야 합니다.
            // 예를 들어, 경로가 /chat/room/{roomId} 형태라면, roomId는 세그먼트 배열의 마지막 요소입니다.
            return segments[segments.length - 1];
        }
        return null; // 적절한 roomId를 찾지 못한 경우
    }
}

