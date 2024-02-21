package io.security.corespringsecurity.service.impl.kbo.games;

import io.security.corespringsecurity.domain.entity.kbo.games.HitterStats;
import io.security.corespringsecurity.repository.kbo.games.HitterStatsRepository;
import io.security.corespringsecurity.service.kbo.games.HitterStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HitterStatsImpl implements HitterStatsService {
    @Autowired
    private HitterStatsRepository hitterStatsRepository;

    @Override
    public void save(HitterStats hitterStats) {
        hitterStatsRepository.save(hitterStats);
    }
}
