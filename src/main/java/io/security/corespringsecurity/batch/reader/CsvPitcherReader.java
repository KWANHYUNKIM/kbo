package io.security.corespringsecurity.batch.reader;

import io.security.corespringsecurity.domain.dto.kbo.HitterDto;
import io.security.corespringsecurity.domain.dto.kbo.PitcherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
@Configuration
@RequiredArgsConstructor
public class CsvPitcherReader {
    public FlatFileItemReader<PitcherDto> csvPitcherReader() {
        FlatFileItemReader<PitcherDto> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new ClassPathResource("/csv/pitcher2023.csv"));
        flatFileItemReader.setEncoding("UTF-8");

        // DelimitedLineTokenizer 설정
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(",");
        delimitedLineTokenizer.setNames("playerName", "teamName", "year", "era", "games", "wins", "loses",
                "saves", "holds", "winPercentage", "inningsPitched", "hits", "homeRunsAllowed", "walks", "hitByPitch", "strikeouts"
        ,"runsAllowed","earnedRuns","walksHitsPerInningPitched");

        // BeanWrapperFieldSetMapper 설정
        BeanWrapperFieldSetMapper<PitcherDto> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(PitcherDto.class);

        // DefaultLineMapper 설정
        DefaultLineMapper<PitcherDto> defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

        // FlatFileItemReader에 LineMapper 설정
        flatFileItemReader.setLineMapper(defaultLineMapper);

        return flatFileItemReader;
    }
}
