package io.security.corespringsecurity.service.impl.kbo;

import io.security.corespringsecurity.domain.entity.kbo.PlayerRecordPitcher;
import io.security.corespringsecurity.repository.kbo.PlayerRecordPitcherRepository;
import io.security.corespringsecurity.service.kbo.PlayerRecordPitcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerRecordPitcherImpl implements PlayerRecordPitcherService {
    @Autowired
    private PlayerRecordPitcherRepository playerRecordPitcherRepository;

    @Override
    public void save(PlayerRecordPitcher playerRecordPitcher){
        playerRecordPitcherRepository.save(playerRecordPitcher);
    }
}
