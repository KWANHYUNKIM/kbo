package io.security.corespringsecurity.repository.kbo.crawl;

import io.security.corespringsecurity.domain.entity.kbo.crawl.TeamYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamYearRepository extends JpaRepository<TeamYear,Long> {

    @Query("SELECT ty FROM TeamYear ty WHERE ty.year = :year")
    List<TeamYear> findAll(@Param("year") String year);
}
