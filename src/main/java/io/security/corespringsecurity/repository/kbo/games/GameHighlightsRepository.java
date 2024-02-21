package io.security.corespringsecurity.repository.kbo.games;

import io.security.corespringsecurity.domain.entity.kbo.games.GameHighlights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameHighlightsRepository extends JpaRepository<GameHighlights,Long> {

}
