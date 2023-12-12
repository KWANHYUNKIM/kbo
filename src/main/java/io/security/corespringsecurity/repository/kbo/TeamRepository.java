package io.security.corespringsecurity.repository.kbo;

import io.security.corespringsecurity.domain.entity.kbo.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity,Long> {
}
