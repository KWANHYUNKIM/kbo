package io.security.corespringsecurity.repository.kbo.games;

import io.security.corespringsecurity.domain.entity.kbo.games.PitcherStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PitcherStatsRepository extends JpaRepository<PitcherStats,Long> {
}
