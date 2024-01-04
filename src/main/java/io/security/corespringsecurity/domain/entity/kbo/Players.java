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
@Table(name = "players")
public class Players {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private long id;

    @Column(name = "jersey_number")
    private String jerseyNumber;

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

    @ManyToOne
    @JoinColumn(name = "teams_id")
    private Teams teams;

    public void setTeam(Teams teams){
        this.teams = teams;
    }

    @Override
    public String toString() {
        return "Players{" +
                "id=" + id +
                ", name='" + name + '\'' +
                // 다른 필드들을 필요에 따라 추가
                '}';
    }
}
