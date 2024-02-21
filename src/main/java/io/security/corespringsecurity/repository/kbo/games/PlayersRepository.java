package io.security.corespringsecurity.repository.kbo.games;

import io.security.corespringsecurity.domain.entity.kbo.Players;
import io.security.corespringsecurity.domain.entity.kbo.crawl.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayersRepository extends JpaRepository<Players,Long> {


    @Query("SELECT p FROM Players p " +
            "WHERE (:team = 'All' OR p.teams.teamName = :team) " +
            "AND (:position = 'All' OR p.position = :position) " +
            "AND (:name = 'All' OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) ")
    List<Players> findByQuery(@Param("team") String team, @Param("position") String position, @Param("name") String name);

    @Query("SELECT p FROM Players p WHERE p.id = :playerId")
    List<Players> findPlayersById(@Param("playerId") Long playerId);
    @Query("SELECT p FROM Players p WHERE p.id IN :playerIds")
    List<Players> findByIdIn(@Param("playerIds") List<Long> playerIds);
    @Query("SELECT p FROM Players  p WHERE p.name = : playerName")
    Players findByPlayer(@Param("playerName") String playerName);

}

