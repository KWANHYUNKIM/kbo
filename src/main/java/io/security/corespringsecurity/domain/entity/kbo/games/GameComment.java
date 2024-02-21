package io.security.corespringsecurity.domain.entity.kbo.games;


import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.BaseTimeEntity;
import io.security.corespringsecurity.domain.entity.schedule.Schedule;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "GameComments")
public class GameComment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name ="GameComment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // Many : Board , One : Member 한명의 유저가 여러개의 게시글 작성
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name ="schedule_id")
    private Schedule schedule;

    private String comment;


}
