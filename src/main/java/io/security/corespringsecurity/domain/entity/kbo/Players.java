package io.security.corespringsecurity.domain.entity.kbo;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "players")
public class Players implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String debutYear;

    @Column(name = "filepath")
    private String filepath; // 파일 경로

    @Column(name = "filename")
    private String filename; // 파일 이름

    @ManyToOne
    @JoinColumn(name = "teams_id")
    private Teams teams;

    @OneToMany(mappedBy = "players")
    private List<PlayerRecord> playerRecord;

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
