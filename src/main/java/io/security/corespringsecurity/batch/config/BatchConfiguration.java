package io.security.corespringsecurity.batch.config;

import io.security.corespringsecurity.batch.reader.CsvPlayerReader;
import io.security.corespringsecurity.batch.reader.CsvTeamYearReader;
import io.security.corespringsecurity.batch.writer.CsvPlayerWriter;
import io.security.corespringsecurity.batch.writer.CsvTeamYearWriter;
import io.security.corespringsecurity.domain.dto.kbo.crawl.TeamYearDto;
import io.security.corespringsecurity.domain.dto.kbo.games.PlayersDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Slf4j
@Configuration
@RequiredArgsConstructor
public class BatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;


    private final CsvTeamYearReader csvTeamYearReader;
    private final CsvTeamYearWriter csvTeamYearWriter;



    private final CsvPlayerReader csvPlayerReader;
    private final CsvPlayerWriter csvPlayerWriter;

    private static final int chunkSize_hitter = 280; // data size , hitter
    private static final int chunkSize_teamYear = 343; // data size, teamYear

    private static final int chunkSize_pitcher = 284; // data size , pitcher

    private static final int chunkSize_player = 100;

    @Bean
    public Job csvTeamYearJob(){
        return jobBuilderFactory.get("csvTeamYearJob")
                .start(csvTeamYearReaderStep())
                .build();
    }

    @Bean
    public Step csvTeamYearReaderStep() {
        return stepBuilderFactory.get("csvTeamYearReaderStep")
                .<TeamYearDto,TeamYearDto> chunk(chunkSize_teamYear)
                .reader(csvTeamYearReader.csvTeamYearReader())
                .writer(csvTeamYearWriter)
                .build();
    }

    /**
     * 타격 성적 2023
     **/


    @Bean
    public Job csvPlayerReaderJob(){
        return jobBuilderFactory.get("csvPlayerReaderJob")
                .start(csvPlayerReaderStep())
                .build();
    }
    @Bean
    public Step csvPlayerReaderStep() {
        return stepBuilderFactory.get("csvPlayerReaderStep")
                .<PlayersDto, PlayersDto>chunk(chunkSize_player)
                .reader(csvPlayerReader.csvPlayerReader())
                .writer(csvPlayerWriter)
                .build();
    }
}
