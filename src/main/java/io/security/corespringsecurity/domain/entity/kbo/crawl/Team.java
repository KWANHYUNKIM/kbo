package io.security.corespringsecurity.domain.entity.kbo.crawl;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue
    @Column(name ="team_id")
    private long id;

    private int ranking;
    private String teamName;
    private int matches;
    private int win;
    private int loss;
    private int draw;
    private double winningPercentage;
    private String gameDifference;
    private String recent10Games;
    private String streak;
    private String home;
    private String away;
}