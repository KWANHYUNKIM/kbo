package io.security.corespringsecurity.domain.entity.profile;

import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.board.Board;
import io.security.corespringsecurity.domain.entity.board.Comment;
import io.security.corespringsecurity.domain.entity.board.Like;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// Notification.java
@Entity
@Setter
@Getter
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @Column(name = "notification_check")
    private boolean check;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "like_id")
    private Like like;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    // Constructors, getters, setters, etc.

    public Notification() {
        // default constructor
    }

    public Notification(String message) {
        this.message = message;
    }

    // additional methods if needed
}
