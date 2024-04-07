package io.security.corespringsecurity.socket.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

//@Getter
//@Setter
//public class ChatRoom {
//
//    @Id // Primary Key임을 나타냅니다.
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID 자동 생성
//    private Long id;
//
//    private String roomId;
//    private String name; // 방 이름
//    private String keyword;
//    private String topic; // 토크 주제
//    private int headcount; // 인원수
//    private String gender;
//    private int minYear;
//    private int maxYear;
//
//
//    private Set<WebSocketSession> sessions = new HashSet<>();
//    @Builder
//    public ChatRoom(String roomId, String name) {
//        this.roomId = roomId;
//        this.name = name;
//    }
//}


@Entity
@Getter
@Setter
@NoArgsConstructor // 기본 생성자를 생성
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomId;

    private String filename;
    private String filepath;

    private String name;
    private String keyword;
    private String topic;
    private int headcount;
    private String gender;
    private int minYear;
    private int maxYear;

    // WebSocketSession은 직렬화할 수 없으므로, JPA 엔티티에 포함시키지 않습니다.
    // private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    // 여기에 추가적인 로직이나 메서드를 포함할 수 있습니다.
}