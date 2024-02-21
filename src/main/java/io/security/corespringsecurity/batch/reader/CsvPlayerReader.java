package io.security.corespringsecurity.batch.reader;

import io.security.corespringsecurity.domain.dto.kbo.crawl.PlayerDto;
import io.security.corespringsecurity.domain.dto.kbo.games.PlayersDto;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@RequiredArgsConstructor
public class CsvPlayerReader {
    public FlatFileItemReader<PlayersDto> csvPlayerReader() {
        FlatFileItemReader<PlayersDto> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new ClassPathResource("/csv/registedPlayer.csv"));
        flatFileItemReader.setEncoding("UTF-8");

        // DelimitedLineTokenizer 설정
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(",");
        delimitedLineTokenizer.setNames("jerseyNumber", "name", "teams", "position", "birthDate", "height", "weight" , "careerHistory","signingBonus","annualSalary","draftRank","debutYear","filepath","filename","players_id");

        // BeanWrapperFieldSetMapper 설정
        BeanWrapperFieldSetMapper<PlayersDto> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(PlayersDto.class);

        // DefaultLineMapper 설정
        DefaultLineMapper<PlayersDto> defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

        // FlatFileItemReader에 LineMapper 설정
        flatFileItemReader.setLineMapper(defaultLineMapper);

        return flatFileItemReader;
    }
}
