package io.security.corespringsecurity.service.impl.kbo;

import io.security.corespringsecurity.domain.entity.kbo.crawl.Player;
import io.security.corespringsecurity.repository.kbo.crawl.PlayerRepository;
import io.security.corespringsecurity.service.kbo.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;
    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public List<Player> findByQuery(String team, String position, String name) {
        return playerRepository.findByQuery(team, position, name);

    }

    @Override
    public Optional<Player> findById(Long playerId) {
        return playerRepository.findById(playerId);
    }

    @Override
    public void save(Player player) { playerRepository.save(player); }

    @Override
    public Player editByPlayer(Long playerId, Player updatedBoard) {
        return playerRepository.editByPlayer(playerId, updatedBoard);
    }

}
