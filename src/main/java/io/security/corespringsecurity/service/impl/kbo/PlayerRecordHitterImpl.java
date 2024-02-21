package io.security.corespringsecurity.service.impl.kbo;

import io.security.corespringsecurity.domain.entity.kbo.PlayerRecordHitter;
import io.security.corespringsecurity.repository.kbo.PlayerRecordHitterRepository;
import io.security.corespringsecurity.service.kbo.PlayerRecordHitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerRecordHitterImpl implements PlayerRecordHitterService {
    @Autowired
    private PlayerRecordHitterRepository playerRecordHitterRepository;
    @Override
    public void save(PlayerRecordHitter playerRecordHitter) {
        playerRecordHitterRepository.save(playerRecordHitter);
    }
}
