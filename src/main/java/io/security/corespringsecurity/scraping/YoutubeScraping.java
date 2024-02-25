package io.security.corespringsecurity.scraping;
import io.security.corespringsecurity.domain.entity.video.Video;
import io.security.corespringsecurity.service.VideoService;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.swing.text.Document;

import java.util.*;
import java.util.stream.Collectors;

import static org.jsoup.nodes.Document.OutputSettings.Syntax.html;


@Component
public class YoutubeScraping {

    @Autowired
    private VideoService videoService;

    public void executeScraping() {

        String chromeDriverPath = "/Users/kwanhyunkim/Downloads/chromedriver-mac-arm64/chromedriver";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        WebDriver driver = new ChromeDriver();

        List<String> teams = Arrays.asList(
                "LG트윈스", "한화이글스", "롯데자이언츠", "두산베어스", "NC다이노스",
                "KT위즈", "SK와이번스", "삼성라이온즈", "KIA타이거즈", "SSG랜더스"
        );

        for (String team : teams) {
            // 각 구단에 대한 YouTube 검색 URL 구성
            String searchUrl = "https://www.youtube.com/results?search_query=" + team.replace(" ", "+");

            driver.get(searchUrl);

            // 검색 결과가 로드될 때까지 대기
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("contents")));

            // 동영상 목록을 가져옴
            List<WebElement> videoList = driver.findElements(By.xpath("//ytd-video-renderer"));
            Set<String> uniqueUrls = new HashSet<>(); // 중복 URL을 확인하기 위한 HashSet

            for (WebElement video : videoList) {
                // 동영상의 제목과 URL 추출
                WebElement titleElement = video.findElement(By.id("video-title"));
                String videoUrl = titleElement.getAttribute("href");

                Video video1 = videoService.findByUrl(videoUrl);

                // URL이 이미 처리되었는지 확인
                if (!uniqueUrls.contains(videoUrl) && video1 == null) {
                    // URL을 HashSet에 추가
                    uniqueUrls.add(videoUrl);

                    // 조회수와 날짜 추출
                    WebElement metaData = video.findElement(By.id("metadata-line"));
                    List<WebElement> spans = metaData.findElements(By.tagName("span"));
                    if (spans.size() >= 2) {
                        String date = spans.get(1).getText(); // 날짜

                        // 썸네일 URL 생성
                        String videoId = videoUrl.split("v=")[1];
                        int ampersandPosition = videoId.indexOf("&");
                        if (ampersandPosition != -1) {
                            videoId = videoId.substring(0, ampersandPosition);
                        }
                        String thumbnailUrl = "https://img.youtube.com/vi/" + videoId + "/maxresdefault.jpg";

                        // 키워드 추출 및 설정
                        List<String> keywords = extractKeywords(titleElement.getText());
                        if (keywords != null && !keywords.isEmpty()) {
                            String keywordsString = keywords.stream()
                                    .map(keyword -> "#" + keyword)
                                    .collect(Collectors.joining(" "));

                            // 각 동영상마다 새로운 videoEntity 인스턴스 생성
                            Video newVideoEntity = new Video();
                            newVideoEntity.setTitle(titleElement.getText());
                            newVideoEntity.setKeyword(keywordsString);
                            newVideoEntity.setType("youtube");
                            newVideoEntity.setDate(date);
                            newVideoEntity.setFilepath(thumbnailUrl);
                            newVideoEntity.setPlayUrl(videoUrl);

                            // videoEntity를 데이터베이스에 저장
                            videoService.save(newVideoEntity);
                        }
                }
            }
        }
    }
        driver.quit();
}

    private List<String> extractKeywords(String text) {
        // 추출하고자 하는 키워드 리스트
        List<String> potentialKeywords = Arrays.asList(
                "한화", "롯데", "두산", "키움", "기아", "LG", "삼성", "NC",
                "Hanwha", "Lotte", "Doosan", "Kiwoom", "KIA", "Samsung", "NC", "KT", "SSG",
                "양현종", "김광현", "이정후", "박병호", "최정", "나성범", "김현수", "오선진", "류현진", "안치홍",
                "Yang Hyeon-jong", "Kim Kwang-hyun", "Lee Jung-hoo", "Park Byung-ho", "Choi Jeong", "Na Sung-bum", "Kim Hyun-soo", "Oh Sun-jin"
        );
        List<String> extractedKeywords = new ArrayList<>();

        // 텍스트를 소문자로 변환하여 대소문자 구분 없이 검색
        String lowerCaseText = text.toLowerCase();

        for (String keyword : potentialKeywords) {
            if (lowerCaseText.contains(keyword.toLowerCase())) {
                extractedKeywords.add(keyword);
            }
        }

        return extractedKeywords;
    }
}

