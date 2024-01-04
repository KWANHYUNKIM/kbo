package io.security.corespringsecurity.domain.entity.kbo;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teams")
public class Teams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="teams_id")
    private long id;
    @Column(name = "teamName")
    private String teamName;

    private String filename;
    private String filepath;

    @OneToMany(mappedBy = "teams")
    private List<Staff> staffs = new ArrayList<Staff>();

    @OneToMany(mappedBy = "teams")
    private List<Players> players = new ArrayList<Players>();


}
