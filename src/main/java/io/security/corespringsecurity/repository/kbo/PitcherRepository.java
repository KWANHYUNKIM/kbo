package io.security.corespringsecurity.repository.kbo;

import io.security.corespringsecurity.domain.entity.kbo.Hitter;
import io.security.corespringsecurity.domain.entity.kbo.Pitcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PitcherRepository extends JpaRepository<Pitcher,Long> {
    Page<Pitcher> findAll(Pageable pageable);

    @Query("SELECT p FROM Pitcher p WHERE p.teamName = :team")
    List<Pitcher> findByTeam(@Param("team") String team);
}
