package io.security.corespringsecurity.repository.kbo.games;

import io.security.corespringsecurity.domain.entity.kbo.games.HitterStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HitterStatsRepository extends JpaRepository<HitterStats,Long> {
}
