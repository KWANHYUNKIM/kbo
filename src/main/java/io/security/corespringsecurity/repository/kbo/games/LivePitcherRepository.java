package io.security.corespringsecurity.repository.kbo.games;

import io.security.corespringsecurity.domain.entity.kbo.games.LivePitcher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivePitcherRepository extends JpaRepository<LivePitcher,Long> {
}
