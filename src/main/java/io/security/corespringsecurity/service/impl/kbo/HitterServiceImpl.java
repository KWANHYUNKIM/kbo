package io.security.corespringsecurity.service.impl.kbo;

import io.security.corespringsecurity.domain.entity.kbo.Hitter;
import io.security.corespringsecurity.repository.kbo.HitterRepository;
import io.security.corespringsecurity.service.kbo.HitterService;
import io.security.corespringsecurity.service.kbo.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HitterServiceImpl implements HitterService {

    @Autowired
    private HitterRepository hitterRepository;

    public HitterServiceImpl(HitterRepository hitterRepository){
        this.hitterRepository = hitterRepository;
    }

    public List<Hitter> getList () {

        return hitterRepository.findAll();
    }


    @Override
    public List<Hitter> getSortedHitterList(String columnName, String team) {

        return this.hitterRepository.sort(columnName,team);
    }

    @Override
    public List<Hitter> findByTeam(String team) {
        return this.hitterRepository.findByTeam(team);
    }

    @Override
    public List<Hitter> findByHomrunTop5(Pageable pageable) {
        return hitterRepository.findByHomerunTop5(pageable);
    }

    @Override
    public List<Hitter> findByHitTop5(Pageable pageable) {
        return hitterRepository.findbyHitTop5(pageable);
    }
}
