package io.security.corespringsecurity.service.impl.kbo.games;

import io.security.corespringsecurity.domain.entity.kbo.games.GameComment;
import io.security.corespringsecurity.repository.kbo.games.GameCommentRepository;
import io.security.corespringsecurity.service.kbo.games.GameCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameCommentImpl implements GameCommentService {
    @Autowired
    private GameCommentRepository gameCommentRepository;
    @Override
    public void save(GameComment comment) {
        gameCommentRepository.save(comment);
    }
}
