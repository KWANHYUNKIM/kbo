package io.security.corespringsecurity.batch.writer;

import io.security.corespringsecurity.domain.dto.kbo.HitterDto;
import io.security.corespringsecurity.domain.dto.kbo.PlayerDto;
import io.security.corespringsecurity.domain.entity.kbo.Hitter;
import io.security.corespringsecurity.domain.entity.kbo.Player;
import io.security.corespringsecurity.repository.kbo.HitterRepository;
import io.security.corespringsecurity.repository.kbo.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

    @Configuration
    @RequiredArgsConstructor
    public class CsvPlayerWriter implements ItemWriter<PlayerDto> {

        private final PlayerRepository playerRepository;

        @Override
        public void write(List<? extends PlayerDto> items) throws Exception {
            List<Player> playerList = new ArrayList<>();

            items.forEach(getPlayerDto -> {
                Player player = getPlayerDto.toEntity();
                playerList.add(player);
            });

            playerRepository.saveAll(playerList);
        }
    }

