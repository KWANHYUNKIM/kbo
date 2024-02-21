package io.security.corespringsecurity.service.kbo.games;

import io.security.corespringsecurity.domain.entity.kbo.games.Entrance;
import io.security.corespringsecurity.domain.entity.kbo.games.EntrancePlayer;

import java.util.List;

public interface EntrancePlayerService {

    List<EntrancePlayer> findByPlayer(long id);

}
