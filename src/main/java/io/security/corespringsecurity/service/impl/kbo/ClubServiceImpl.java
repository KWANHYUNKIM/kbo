package io.security.corespringsecurity.service.impl.kbo;

import io.security.corespringsecurity.domain.entity.kbo.Teams;
import io.security.corespringsecurity.repository.kbo.games.ClubRepository;
import io.security.corespringsecurity.service.kbo.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService {

    @Autowired
    ClubRepository clubRepository;

    @Transactional
    @Override
    public void createClub(Teams teams) {
        clubRepository.save(teams);
    }

    @Override
    public List<Teams> findAll() {
        return clubRepository.findAll();
    }

    @Override
    public Optional<Teams> findById(Long teamsId) {
        return clubRepository.findById(teamsId);
    }

    @Override
    public Teams findByTeamName(String teamName) {
        return clubRepository.findByTeamName(teamName);
    }
}
