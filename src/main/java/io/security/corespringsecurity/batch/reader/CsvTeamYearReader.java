package io.security.corespringsecurity.batch.reader;

import io.security.corespringsecurity.domain.dto.kbo.HitterDto;
import io.security.corespringsecurity.domain.dto.kbo.TeamYearDto;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@RequiredArgsConstructor
public class CsvTeamYearReader {
    public FlatFileItemReader<TeamYearDto> csvTeamYearReader() {
        FlatFileItemReader<TeamYearDto> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new ClassPathResource("/csv/teamyear.csv"));
        flatFileItemReader.setEncoding("UTF-8");

        // DelimitedLineTokenizer 설정

        // Todo :  ranking [1] 값이 오류가 나옴 UTF-8 with BOM error
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(",");
        delimitedLineTokenizer.setNames("ranking", "teamName", "matches", "win", "loss", "draw", "winningPercentage",
                "gameDifference", "recent10Games", "streak", "home", "away", "year");

        // BeanWrapperFieldSetMapper 설정
        BeanWrapperFieldSetMapper<TeamYearDto> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(TeamYearDto.class);

        // DefaultLineMapper 설정
        DefaultLineMapper<TeamYearDto> defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

        // FlatFileItemReader에 LineMapper 설정
        flatFileItemReader.setLineMapper(defaultLineMapper);

        return flatFileItemReader;
    }
}
