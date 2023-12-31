package io.security.corespringsecurity.repository.kbo;

import io.security.corespringsecurity.domain.entity.kbo.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player,Long> {
    @Query("SELECT p FROM Player p " +
            "WHERE (:team = 'All' OR p.team = :team) " +
            "AND (:position = 'All' OR p.position = :position) " +
            "AND (:name = 'All' OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) ")
    List<Player> findByQuery(@Param("team") String team, @Param("position") String position, @Param("name") String name);

}
