package io.security.corespringsecurity;

import io.security.corespringsecurity.scraping.KboScraping;
import io.security.corespringsecurity.scraping.YoutubeScraping;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;


@SpringBootApplication
@EnableJpaAuditing
@EnableBatchProcessing
public class CoreSpringSecurityApplication {

    public static void main(String[] args) {

        SpringApplication.run(CoreSpringSecurityApplication.class, args);
    }
//    @Bean
//    public CommandLineRunner commandLineRunner(KboScraping kboScraping) {
//        return args -> {
//            kboScraping.executeScraping();
//        };
//    }
//    @Bean
//    public CommandLineRunner commandLineRunner(YoutubeScraping youtubeScraping) {
//        return args -> {
//            youtubeScraping.executeScraping();
//        };
//    }
}

// 9월 9일 더블헤더로 인해 데이터값 오류
// 9월 17일 더블헤더로 인해 데이터값 오류
// 9월 28일 더블헤더로 인해 데이터값 오류
// 10월 08일 수원 14:00 경기 한화, KT
// 10월 08일 광주 14:00 경기 삼성 타이거즈
