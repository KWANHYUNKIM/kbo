package io.security.corespringsecurity.service.impl.kbo;

import io.security.corespringsecurity.domain.entity.kbo.Teams;
import io.security.corespringsecurity.domain.entity.kbo.crawl.Team;
import io.security.corespringsecurity.repository.kbo.games.TeamsRepository;
import io.security.corespringsecurity.service.kbo.TeamsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TeamsServiceImpl implements TeamsService {

    @Autowired
    private TeamsRepository teamsRepository;

    @Transactional
    @Override
    public List<Teams> findByTeam(String team) {
        return teamsRepository.findByTeam(team);
    }
}
