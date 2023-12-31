package io.security.corespringsecurity.batch.reader;

import io.security.corespringsecurity.domain.dto.kbo.HitterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@RequiredArgsConstructor
public class CsvHitterReader {
    public FlatFileItemReader<HitterDto> csvHitterReader() {
        FlatFileItemReader<HitterDto> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new ClassPathResource("/csv/hitter2023.csv"));
        flatFileItemReader.setEncoding("UTF-8");

        // DelimitedLineTokenizer 설정
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(",");
        delimitedLineTokenizer.setNames("playerName", "teamName", "year", "avg", "games", "plateAppearances", "atBats",
                "runs", "hits", "doubles", "triples", "homeRuns", "totalBases", "rbi", "sac", "sf");

        // BeanWrapperFieldSetMapper 설정
        BeanWrapperFieldSetMapper<HitterDto> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(HitterDto.class);

        // DefaultLineMapper 설정
        DefaultLineMapper<HitterDto> defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

        // FlatFileItemReader에 LineMapper 설정
        flatFileItemReader.setLineMapper(defaultLineMapper);

        return flatFileItemReader;
    }
}