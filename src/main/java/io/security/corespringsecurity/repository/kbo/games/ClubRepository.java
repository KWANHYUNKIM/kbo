package io.security.corespringsecurity.repository.kbo.games;

import io.security.corespringsecurity.domain.entity.kbo.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ClubRepository extends JpaRepository<Teams,Long> {

    @Query("SELECT t FROM Teams t WHERE t.teamName = :teamName")
    Teams findByTeamName(@Param("teamName") String teamName);

}
