package io.security.corespringsecurity.repository.kbo;

import io.security.corespringsecurity.domain.entity.kbo.Hitter;
import io.security.corespringsecurity.domain.entity.kbo.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HitterRepository extends JpaRepository<Hitter,Long> {

    // 특정 컬럼을 기준으로 정렬하는 쿼리
    @Query("SELECT h FROM Hitter h " +
            "WHERE (COALESCE(:team, 'All') = 'All' OR h.teamName = :team) " +
            "ORDER BY " +
            "CASE WHEN :columnName IS NULL OR :columnName = 'All' THEN h.avg END DESC, " +
            "CASE WHEN :columnName = 'games' THEN h.games END DESC, " +
            "CASE WHEN :columnName = 'atBats' THEN h.atBats END DESC, " +
            "CASE WHEN :columnName = 'runs' THEN h.runs END DESC, " +
            "CASE WHEN :columnName = 'hits' THEN h.hits END DESC, " +
            "CASE WHEN :columnName = 'doubles' THEN h.doubles END DESC, " +
            "CASE WHEN :columnName = 'triples' THEN h.triples END DESC, " +
            "CASE WHEN :columnName = 'homeRuns' THEN h.homeRuns END DESC, " +
            "CASE WHEN :columnName = 'totalBases' THEN h.totalBases END DESC, " +
            "CASE WHEN :columnName = 'rbi' THEN h.rbi END DESC, " +
            "CASE WHEN :columnName = 'sac' THEN h.sac END DESC, " +
            "CASE WHEN :columnName = 'sf' THEN h.sf END DESC")
    List<Hitter> sort(@Param("columnName") String columnName, @Param("team") String team);


    @Query("SELECT t FROM Hitter t WHERE t.teamName = :team ORDER BY t.teamName DESC")
    List<Hitter> findByTeam(@Param("team") String team);

}
