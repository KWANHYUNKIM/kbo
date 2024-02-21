package io.security.corespringsecurity.service.impl.kbo.games;

import io.security.corespringsecurity.domain.entity.kbo.games.PitcherStats;
import io.security.corespringsecurity.repository.kbo.games.PitcherStatsRepository;
import io.security.corespringsecurity.service.kbo.games.PitcherStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PitcherStatsImpl implements PitcherStatsService {
    @Autowired
    PitcherStatsRepository pitcherStatsRepository;

    @Override
    public void save(List<PitcherStats> pitcherStats) {
        pitcherStatsRepository.saveAll(pitcherStats);
    }
}
