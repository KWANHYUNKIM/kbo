package io.security.corespringsecurity.repository.kbo.games;

import io.security.corespringsecurity.domain.entity.kbo.Teams;
import io.security.corespringsecurity.domain.entity.kbo.crawl.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TeamsRepository extends JpaRepository<Teams,Long> {

    @Query("SELECT t FROM Teams t WHERE t.teamName = :team")
    List<Teams> findByTeam(@Param("team") String team);

}
