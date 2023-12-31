package io.security.corespringsecurity.batch.writer;

import io.security.corespringsecurity.domain.dto.kbo.HitterDto;
import io.security.corespringsecurity.domain.dto.kbo.PitcherDto;
import io.security.corespringsecurity.domain.entity.kbo.Hitter;
import io.security.corespringsecurity.domain.entity.kbo.Pitcher;
import io.security.corespringsecurity.repository.kbo.HitterRepository;
import io.security.corespringsecurity.repository.kbo.PitcherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CsvPitcherWriter implements ItemWriter<PitcherDto> {

    private final PitcherRepository pitcherRepository;

    @Override
    public void write(List<? extends PitcherDto> items) throws Exception {
        List<Pitcher> pitcherList = new ArrayList<>();

        items.forEach(getPitcherDto -> {
            Pitcher pitcher = getPitcherDto.toEntity();
            pitcherList.add(pitcher);
        });

        pitcherRepository.saveAll(pitcherList);
    }
}