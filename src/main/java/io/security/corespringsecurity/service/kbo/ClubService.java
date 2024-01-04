package io.security.corespringsecurity.service.kbo;

import io.security.corespringsecurity.domain.entity.kbo.Teams;

import java.util.List;
import java.util.Optional;

public interface ClubService {

    void createClub(Teams teams);

    List<Teams> findAll();

    Optional<Teams> findById(Long teamsId);

    Teams findByTeamName (String teamName);

}
