package io.security.corespringsecurity.repository.kbo.crawl;

import io.security.corespringsecurity.domain.entity.kbo.crawl.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {
    @Query("SELECT t FROM Team t WHERE t.teamName = :team")
    List<Team> findByTeam(@Param("team") String team);

}