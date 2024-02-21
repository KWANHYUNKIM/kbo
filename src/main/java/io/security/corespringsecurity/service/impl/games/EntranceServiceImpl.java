package io.security.corespringsecurity.service.impl.games;

import io.security.corespringsecurity.domain.entity.kbo.games.Entrance;
import io.security.corespringsecurity.repository.kbo.games.EntranceRepository;
import io.security.corespringsecurity.service.kbo.games.EntranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntranceServiceImpl implements EntranceService  {

    @Autowired
    EntranceRepository entranceRepository;

    @Override
    public void save(Entrance entrance) {
        entranceRepository.save(entrance);
    }
}
