package io.security.corespringsecurity.repository.kbo.games;

import io.security.corespringsecurity.domain.entity.kbo.games.LiveTeamStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiveTeamStatsRepository extends JpaRepository<LiveTeamStats,Long> {
}
