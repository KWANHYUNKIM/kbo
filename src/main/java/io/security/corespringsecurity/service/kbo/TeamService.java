package io.security.corespringsecurity.service.kbo;


import io.security.corespringsecurity.domain.entity.kbo.Hitter;
import io.security.corespringsecurity.domain.entity.kbo.Team;
import io.security.corespringsecurity.domain.entity.kbo.TeamYear;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface TeamService {


    void createTeam() throws IOException;


    List<Team> findByTeam(String team);
}