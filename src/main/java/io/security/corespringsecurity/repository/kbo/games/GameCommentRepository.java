package io.security.corespringsecurity.repository.kbo.games;

import io.security.corespringsecurity.domain.entity.kbo.games.GameComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameCommentRepository extends JpaRepository<GameComment,Long> {
}
