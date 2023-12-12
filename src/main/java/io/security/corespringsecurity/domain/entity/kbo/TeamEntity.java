package io.security.corespringsecurity.domain.entity.kbo;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teams")
public class TeamEntity {

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