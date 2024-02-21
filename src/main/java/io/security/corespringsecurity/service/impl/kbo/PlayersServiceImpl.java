package io.security.corespringsecurity.service.impl.kbo;

import io.micrometer.core.util.internal.logging.AbstractInternalLogger;
import io.security.corespringsecurity.domain.entity.kbo.Players;
import io.security.corespringsecurity.domain.entity.kbo.crawl.Player;
import io.security.corespringsecurity.repository.kbo.crawl.PlayerRepository;
import io.security.corespringsecurity.repository.kbo.games.PlayersRepository;
import io.security.corespringsecurity.service.kbo.PlayersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayersServiceImpl implements PlayersService {

    @Autowired
    private PlayersRepository playersRepository;

    @Override
    public Players findByPlayer(String playerName) {
        return playersRepository.findByPlayer(playerName);
    }

    @Override
    public List<Players> findAll() {
        return playersRepository.findAll();
    }

    @Override
    public List<Players> findByQuery(String team, String position, String name) {

        System.out.println("playerServiceImpl" + team + position + name);

        return playersRepository.findByQuery(team, position, name);
    }

    @Override
    public List<Players> findById(Long playerId) {
        return playersRepository.findPlayersById(playerId);
    }

    @Override
    public Players findByPlayer(Long playerId) {
        return playersRepository.getOne(playerId);
    }

    @Override
    public List<Players> findByIdIn(List<Long> entryCandidates) {
        return playersRepository.findByIdIn(entryCandidates);
    }

    @Override
    public Players save(Players players1) {
         return playersRepository.save(players1);
    }


//    @Override
//    public void save(Player player) { playersRepository.save(player); }
//
//    @Override
//    public Player editByPlayer(Long playerId, Player updatedBoard) {
//        return playersRepository.editByPlayer(playerId, updatedBoard);
//    }

}
