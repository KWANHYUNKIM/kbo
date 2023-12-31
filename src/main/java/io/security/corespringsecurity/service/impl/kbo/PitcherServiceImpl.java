package io.security.corespringsecurity.service.impl.kbo;

import io.security.corespringsecurity.domain.entity.kbo.Pitcher;
import io.security.corespringsecurity.repository.kbo.PitcherRepository;
import io.security.corespringsecurity.service.kbo.PitcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PitcherServiceImpl implements PitcherService {

    @Autowired
    PitcherRepository pitcherRepository;

    @Override
    public Page<Pitcher> getList(int page) {
        Pageable pageable = PageRequest.of(page,30);
        return this.pitcherRepository.findAll(pageable);
    }

    @Override
    public List<Pitcher> findByTeam(String team) {
        return pitcherRepository.findByTeam(team);
    }
}
