package io.security.corespringsecurity.service.impl.kbo;

import io.security.corespringsecurity.domain.entity.kbo.PlayerRecord;
import io.security.corespringsecurity.domain.entity.kbo.Players;
import io.security.corespringsecurity.repository.kbo.PlayerRecordRepository;
import io.security.corespringsecurity.service.kbo.PlayerRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerRecordServiceImpl implements PlayerRecordService {
    @Autowired
    PlayerRecordRepository playerRecordRepository;


    @Override
    public List<PlayerRecord> findByIdList(List<Players> players) {
        return playerRecordRepository.findByIdList(players);
    }

    @Override
    public List<Object []> findByTotalList(List<Players> player) {
        return playerRecordRepository.findByTotalList(player);
    }

    @Override
    public List<PlayerRecord> findRecent10Games(List<Players> player, Pageable pageable) {
        return playerRecordRepository.findRecent10Games(player, pageable);
    }

    @Override
    public List<PlayerRecord> findByDetailsHitter(List<Players> player) {
        return playerRecordRepository.findByDetailsHitter(player);
    }

}
