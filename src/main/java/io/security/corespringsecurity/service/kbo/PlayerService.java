package io.security.corespringsecurity.service.kbo;


import io.security.corespringsecurity.domain.entity.kbo.crawl.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerService  {
    List<Player> findAll();

    List<Player> findByQuery(String team, String position,String name);

    Optional<Player> findById(Long playerId);

    void save(Player player);

    Player editByPlayer(Long playerId, Player updatedBoard);
}
