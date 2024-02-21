package io.security.corespringsecurity.repository.kbo;

import io.security.corespringsecurity.domain.entity.kbo.PlayerRecordPitcher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRecordPitcherRepository extends JpaRepository<PlayerRecordPitcher,Long> {
}
