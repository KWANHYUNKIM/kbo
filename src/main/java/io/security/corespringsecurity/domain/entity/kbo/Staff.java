package io.security.corespringsecurity.domain.entity.kbo;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "staffs")
public class Staff implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="staff_id")
    private long id;

    @Column(name ="name")
    private String name;

    @Column(name = "position")
    private String position;

    @Column(name = "jerseynumber")
    private String jerseyNumber;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "height")
    private String height;

    @Column(name = "weight")
    private String weight;

    @Column(name = "career_history")
    private String careerHistory;

    @Column(name = "profile")
    private String profile;

    @ManyToOne
    @JoinColumn(name = "teams_id")
    private Teams teams;

    public void setTeam(Teams teams){
        this.teams = teams;
    }
}
