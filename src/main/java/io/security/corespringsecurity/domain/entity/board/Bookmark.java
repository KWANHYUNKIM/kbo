package io.security.corespringsecurity.domain.entity.board;

import io.security.corespringsecurity.domain.entity.Account;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "bookmarks")
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "bookmark_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    // 생성자, 게터, 세터 등 필요한 메서드 추가
}
