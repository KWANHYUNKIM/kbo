package io.security.corespringsecurity.socket;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@RequiredArgsConstructor
@Configuration
@EnableWebSocket   //이게 websocket 서버로서 동작하겠다는 어노테이션
public class WebSocketConfig implements WebSocketConfigurer {

    private final WebSocketHandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/ws/chat/{roomId}")
                .setAllowedOrigins("*");
        // 이제 서버는 /ws/chat/{roomId} 형태의 경로로 들어오는 WebSocket 연결을 처리할 수 있습니다.
    }
}