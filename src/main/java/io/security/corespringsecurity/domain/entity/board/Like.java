package io.security.corespringsecurity.domain.entity.board;

import io.security.corespringsecurity.domain.entity.Account;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    private int likes; // 좋아요

    private int fan; // 팬이에요

    private int sad; // 슬퍼요

    private int angry; // 화나요

    private int follow; // 후속기사원해요

}

