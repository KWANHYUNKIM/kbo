package io.security.corespringsecurity.domain.entity.kbo.crawl;

import io.security.corespringsecurity.domain.entity.board.Board;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Member;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "player")
public class Player implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private long id;

    @Column(name = "jersey_number")
    private String jerseyNumber;

    @Column(name ="team")
    private String team;

    @Column(name = "name")
    private String name;

    @Column(name = "position")
    private String position;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "height")
    private String height;

    @Column(name = "weight")
    private String weight;

    @Column(name = "career_history")
    private String careerHistory;

    @Column(name = "signing_bonus")
    private Integer signingBonus;

    @Column(name = "annual_salary")
    private Integer annualSalary;

    @Column(name = "draft_rank")
    private String draftRank;

    @Column(name = "debutyear")
    private Integer debutYear;

    @Column(name = "filepath")
    private String filepath; // 파일 경로

    @Column(name = "filename")
    private String filename; // 파일 이름


}
