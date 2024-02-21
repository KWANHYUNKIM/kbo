package io.security.corespringsecurity.service.kbo.games;

import io.security.corespringsecurity.domain.entity.kbo.games.GameBoardEntity;

import java.util.List;

public interface GameBoardService {

    GameBoardEntity save(GameBoardEntity gameBoardEntity);

    List<GameBoardEntity> findByBoard(String crowd, String startTime, String endTime, String runTime);
}
