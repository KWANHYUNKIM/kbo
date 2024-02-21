package io.security.corespringsecurity.service.kbo;

import io.security.corespringsecurity.domain.entity.kbo.Teams;
import io.security.corespringsecurity.domain.entity.kbo.crawl.Team;

import java.util.List;

public interface TeamsService {
    List<Teams> findByTeam(String team);
}
