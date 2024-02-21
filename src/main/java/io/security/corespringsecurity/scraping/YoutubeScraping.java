package io.security.corespringsecurity.scraping;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.text.Document;

import static org.jsoup.nodes.Document.OutputSettings.Syntax.html;

public class YoutubeScraping {
    public static void main(String[] args) {

        String chromeDriverPath = "/Users/kwanhyunkim/Downloads/chromedriver-mac-arm64/chromedriver";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.youtube.com/");

        WebElement searchBox = driver.findElement(By.name("search_query"));

        // 검색어 입력 및 검색 실행
        searchBox.sendKeys("LG트윈스");
        searchBox.submit();

        // 검색 결과가 로드될 때까지 대기
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement firstVideo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("video-title")));

        // 첫 번째 동영상의 URL 추출
        String firstVideoUrl = firstVideo.getAttribute("href");
        System.out.println("First video URL: " + firstVideoUrl);

    }
}


