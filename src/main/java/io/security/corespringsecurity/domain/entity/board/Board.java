package io.security.corespringsecurity.domain.entity.board;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "board")
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name ="board_id")
    private Long id;

    private String title;

    @Lob // 대용량 데이터
    private String content;

    private String filename; // 파일 이름

    private String filepath; // 파일 경로

    @ManyToOne(fetch = FetchType.LAZY) // Many : Board , One : Member 한명의 유저가 여러개의 게시글 작성
    @JoinColumn(name = "account_id")
    private Account account;

    @ColumnDefault("0")
    private int viewCount;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Like> likes;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<Comment> comments;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Bookmark> bookmarks;


    //==연관 관계 메서드==//
    public void setAcount (Account account) {
        this.account = account;
    }

    //==비즈니스 로직==//



}
