package io.security.corespringsecurity.repository.kbo;

import io.security.corespringsecurity.domain.entity.kbo.PlayerRecord;
import io.security.corespringsecurity.domain.entity.kbo.Players;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerRecordRepository extends JpaRepository<PlayerRecord, Long> {

    @Query("SELECT pr FROM PlayerRecord pr JOIN pr.playerRecordHitter prh WHERE pr.players IN :players")
    List<PlayerRecord> findByIdList(@Param("players") List<Players> players);

    @Query("SELECT " +
            "SUM(pr.playerRecordHitter.pa), " +
            "SUM(pr.playerRecordHitter.ab), " +
            "SUM(pr.playerRecordHitter.r), " +
            "SUM(pr.playerRecordHitter.h), " +
            "SUM(pr.playerRecordHitter.doubles), " +
            "SUM(pr.playerRecordHitter.triples), " +
            "SUM(pr.playerRecordHitter.hr), " +
            "SUM(pr.playerRecordHitter.rbi), " +
            "SUM(pr.playerRecordHitter.sb), " +
            "SUM(pr.playerRecordHitter.cs), " +
            "SUM(pr.playerRecordHitter.bb), " +
            "SUM(pr.playerRecordHitter.hbp), " +
            "SUM(pr.playerRecordHitter.so), " +
            "SUM(pr.playerRecordHitter.gdp), " +
            "pr.playerRecordHitter.avg2 " +
            "FROM PlayerRecord pr WHERE pr.players IN :players " +
            "GROUP BY pr.playerRecordHitter.avg2")
    List<Object []> findByTotalList(@Param("players") List<Players> player);

    @Query("SELECT pr FROM PlayerRecord pr WHERE pr.players IN :players ORDER BY pr.playerRecordHitter.date DESC")
    List<PlayerRecord> findRecent10Games(@Param("players") List<Players> players, Pageable pageable);

    @Query("SELECT pr FROM PlayerRecord pr JOIN pr.detailsRecordHitter prh WHERE prh.id IS NOT NULL AND pr.players IN :players")
    List<PlayerRecord> findByDetailsHitter(@Param("players") List<Players> players);}
