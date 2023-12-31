package io.security.corespringsecurity.batch.writer;

import io.security.corespringsecurity.domain.dto.kbo.HitterDto;
import io.security.corespringsecurity.domain.dto.kbo.TeamYearDto;
import io.security.corespringsecurity.domain.entity.kbo.Hitter;
import io.security.corespringsecurity.domain.entity.kbo.TeamYear;
import io.security.corespringsecurity.repository.kbo.HitterRepository;
import io.security.corespringsecurity.repository.kbo.TeamYearRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CsvTeamYearWriter implements ItemWriter<TeamYearDto> {

    private final TeamYearRepository teamYearRepository;

    @Override
    public void write(List<? extends TeamYearDto> items) throws Exception {
        List<TeamYear> teamYearList = new ArrayList<>();

        items.forEach(getTeamYearDto -> {
            TeamYear teamyear = getTeamYearDto.toEntity();
            teamYearList.add(teamyear);
        });

        teamYearRepository.saveAll(teamYearList);
    }
}