package io.security.corespringsecurity.service.kbo;

import io.security.corespringsecurity.domain.entity.kbo.PlayerRecord;
import io.security.corespringsecurity.domain.entity.kbo.Players;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PlayerRecordService {
    List<PlayerRecord> findByIdList(List<Players> players);

    List<Object []> findByTotalList(List<Players> player);

    List<PlayerRecord> findRecent10Games(List<Players> player, Pageable pageable);

    List<PlayerRecord> findByDetailsHitter(List<Players> player);
}
