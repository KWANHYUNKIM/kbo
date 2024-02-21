package io.security.corespringsecurity.repository.kbo.games;


import io.security.corespringsecurity.domain.entity.kbo.games.GameBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameBoardRepository extends JpaRepository<GameBoardEntity,Long> {
    @Query("SELECT g FROM GameBoardEntity g WHERE g.crowd = :crowd AND g.startTime = :startTime AND g.endTime = :endTime AND g.runTime = :runTime")
    List<GameBoardEntity> findByBoard(@Param("crowd") String crowd,
                                      @Param("startTime") String startTime,
                                      @Param("endTime") String endTime,
                                      @Param("runTime") String runTime);}
