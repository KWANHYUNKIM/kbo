package io.security.corespringsecurity.service.kbo;

import io.security.corespringsecurity.domain.entity.kbo.Hitter;
import io.security.corespringsecurity.domain.entity.kbo.Pitcher;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PitcherService {
    Page<Pitcher> getList (int page);

    List<Pitcher> findByTeam(String team);
}
