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
@Table(name = "teamyears")
public class TeamYear {
    @Id
    @GeneratedValue
    @Column(name ="teamyear_id")
    private long id;

    private String ranking;
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
    private String year;
}
