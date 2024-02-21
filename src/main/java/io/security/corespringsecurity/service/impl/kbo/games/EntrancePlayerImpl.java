package io.security.corespringsecurity.service.impl.kbo.games;


import io.security.corespringsecurity.domain.entity.kbo.games.Entrance;
import io.security.corespringsecurity.domain.entity.kbo.games.EntrancePlayer;
import io.security.corespringsecurity.repository.kbo.games.EntrancePlayerRepository;
import io.security.corespringsecurity.service.kbo.games.EntrancePlayerService;
import io.security.corespringsecurity.service.kbo.games.EntranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrancePlayerImpl implements EntrancePlayerService {
    @Autowired
    private EntrancePlayerRepository entrancePlayerRepository;
    @Override
    public List<EntrancePlayer> findByPlayer(long id) {
        return entrancePlayerRepository.findByPlayer(id);
    }
}
