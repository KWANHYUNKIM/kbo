package io.security.corespringsecurity.service.impl.kbo.games;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
@RequiredArgsConstructor

public class GamesServiceImpl {

    private static String homepage = "https://www.koreabaseball.com/Schedule/GameCenter/Main.aspx#none;";

    @PostConstruct
    public void createGames() throws IOException {
        Document document = Jsoup.connect(homepage).get();
        System.out.println("document 값은?"+ document);
    }
}
