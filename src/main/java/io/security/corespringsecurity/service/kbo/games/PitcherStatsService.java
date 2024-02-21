package io.security.corespringsecurity.service.kbo.games;

import io.security.corespringsecurity.domain.entity.kbo.games.PitcherStats;

import java.util.List;

public interface PitcherStatsService {

    void save(List<PitcherStats> pitcherStats);
}
