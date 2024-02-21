package io.security.corespringsecurity.service.kbo;

import io.security.corespringsecurity.domain.entity.kbo.Players;
import io.security.corespringsecurity.domain.entity.kbo.crawl.Player;

import java.util.List;
import java.util.Optional;

public interface PlayersService {

    Players findByPlayer(String playerName);

    List<Players> findAll();

    List<Players> findByQuery(String team, String position, String name);

    List<Players> findById(Long playerId);

    Players findByPlayer(Long playerId);

    List<Players> findByIdIn(List<Long> entryCandidates);

    Players save(Players players1);



//
//    void save(Player player);
//
//    Player editByPlayer(Long playerId, Player updatedBoard);

}
