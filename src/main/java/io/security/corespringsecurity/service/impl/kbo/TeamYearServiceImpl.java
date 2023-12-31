package io.security.corespringsecurity.service.impl.kbo;

import io.security.corespringsecurity.domain.entity.kbo.TeamYear;
import io.security.corespringsecurity.repository.kbo.TeamRepository;
import io.security.corespringsecurity.repository.kbo.TeamYearRepository;
import io.security.corespringsecurity.service.kbo.TeamService;
import io.security.corespringsecurity.service.kbo.TeamYearService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamYearServiceImpl implements TeamYearService {

    @Autowired
    private final TeamYearRepository teamYearRepository;

    @Override
    public List<TeamYear> findAll(String year) {
        return teamYearRepository.findAll(year);
    }

    @Override
    public List<TeamYear> findAll() {
        return teamYearRepository.findAll();
    }
}
