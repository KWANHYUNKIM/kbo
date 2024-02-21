package io.security.corespringsecurity.service.impl.kbo.games;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class StandaloneRunner {

    private static String homepage = "https://www.koreabaseball.com/Schedule/GameCenter/Main.aspx#none;";

    public static void main(String[] args) {
        try {
            createGames();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createGames() throws IOException {
        Document document = Jsoup.connect(homepage).get();
        System.out.println("document 값은? " + document);
    }
}
