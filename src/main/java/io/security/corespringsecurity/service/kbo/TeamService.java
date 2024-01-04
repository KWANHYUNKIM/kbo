package io.security.corespringsecurity.service.kbo;


import io.security.corespringsecurity.domain.entity.kbo.crawl.Team;

import java.io.IOException;
import java.util.List;

public interface TeamService {


    void createTeam() throws IOException;

    List<Team> findByTeam(String team);
}