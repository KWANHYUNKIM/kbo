package io.security.corespringsecurity.batch.writer;

import io.security.corespringsecurity.domain.dto.kbo.HitterDto;
import io.security.corespringsecurity.domain.entity.kbo.Hitter;
import io.security.corespringsecurity.repository.kbo.HitterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CsvHitterWriter implements ItemWriter<HitterDto> {

    private final HitterRepository hitterRepository;

    @Override
    public void write(List<? extends HitterDto> items) throws Exception {
        List<Hitter> hitterList = new ArrayList<>();

        items.forEach(getHitterDto -> {
            Hitter hitter = getHitterDto.toEntity();
            hitterList.add(hitter);
        });

        hitterRepository.saveAll(hitterList);
    }
}