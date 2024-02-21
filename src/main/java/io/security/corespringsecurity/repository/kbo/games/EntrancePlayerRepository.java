package io.security.corespringsecurity.repository.kbo.games;

import io.security.corespringsecurity.domain.entity.kbo.games.EntrancePlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EntrancePlayerRepository extends JpaRepository<EntrancePlayer,Long> {

    @Query("SELECT e FROM EntrancePlayer e WHERE e.entrance.id = :id")
    List<EntrancePlayer> findByPlayer(@Param("id") Long id);
}
