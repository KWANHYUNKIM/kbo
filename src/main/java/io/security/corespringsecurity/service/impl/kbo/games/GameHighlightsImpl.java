package io.security.corespringsecurity.service.impl.kbo.games;

import io.security.corespringsecurity.domain.entity.kbo.games.GameHighlights;
import io.security.corespringsecurity.repository.kbo.games.GameHighlightsRepository;
import io.security.corespringsecurity.service.kbo.games.GameHighlightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameHighlightsImpl implements GameHighlightsService {
    @Autowired
    GameHighlightsRepository gameHighlightsRepository;
    @Override
    public void save(GameHighlights gameHighlights) {
         gameHighlightsRepository.save(gameHighlights);
    }
}
