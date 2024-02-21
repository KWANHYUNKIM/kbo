package io.security.corespringsecurity.service.impl.kbo.games;

import io.security.corespringsecurity.domain.entity.kbo.games.GameBoardEntity;
import io.security.corespringsecurity.repository.kbo.games.GameBoardRepository;
import io.security.corespringsecurity.service.kbo.games.GameBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameBoardImpl implements GameBoardService {

    @Autowired
    private GameBoardRepository gameBoardRepository;

    @Override
    public GameBoardEntity save(GameBoardEntity gameBoardEntity) {
        return gameBoardRepository.save(gameBoardEntity);
    }

    @Override
    public List<GameBoardEntity> findByBoard(String crowd, String startTime, String endTime, String runTime) {
        return gameBoardRepository.findByBoard(crowd,startTime,endTime,runTime);
    }
}
