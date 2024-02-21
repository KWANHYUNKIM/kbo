package io.security.corespringsecurity.batch.writer;

import io.security.corespringsecurity.domain.dto.kbo.crawl.PlayerDto;
import io.security.corespringsecurity.domain.dto.kbo.games.PlayersDto;
import io.security.corespringsecurity.domain.entity.kbo.Players;
import io.security.corespringsecurity.domain.entity.kbo.crawl.Player;
import io.security.corespringsecurity.repository.kbo.crawl.PlayerRepository;
import io.security.corespringsecurity.repository.kbo.games.PlayersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

    @Configuration
    @RequiredArgsConstructor
    public class CsvPlayerWriter implements ItemWriter<PlayersDto> {

        private final PlayersRepository playersRepository;

        @Override
        public void write(List<? extends PlayersDto> items) throws Exception {
            List<Players> playerList = new ArrayList<>();
            items.forEach(getPlayersDto -> {
                Players player = getPlayersDto.toEntity();
                playerList.add(player);
            });

            playersRepository.saveAll(playerList);
        }
    }

