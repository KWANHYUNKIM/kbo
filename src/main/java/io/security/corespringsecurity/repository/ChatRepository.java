package io.security.corespringsecurity.repository;

import io.security.corespringsecurity.socket.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChatRepository extends JpaRepository<ChatRoom,Long> {
    @Query("SELECT c FROM ChatRoom c WHERE c.roomId = :roomId")
    ChatRoom findByRoomId(@Param("roomId") String roomId);

}
