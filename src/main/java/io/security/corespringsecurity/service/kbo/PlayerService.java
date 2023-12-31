package io.security.corespringsecurity.service.kbo;


import io.security.corespringsecurity.domain.entity.kbo.Player;

import java.util.List;

public interface PlayerService  {
    List<Player> findAll();

    List<Player> findByQuery(String team, String position,String name);
}
