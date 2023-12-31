package io.security.corespringsecurity.service.impl.kbo;

import io.security.corespringsecurity.repository.kbo.BaseballTeamStatsHitterRepository;
import io.security.corespringsecurity.service.kbo.BaseballTeamStatsHitterService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BaseballTeamStatsHitterHitterServiceImpl implements BaseballTeamStatsHitterService {
    private static String homepage = "https://www.koreabaseball.com/Record/Team/Hitter/Basic1.aspx";

    @Autowired
    BaseballTeamStatsHitterRepository baseballTeamStatsHitterRepository;

//    @PostConstruct
//    public void createTeam() throws IOException {
//        try {
//            // 웹 페이지에 연결
//            String url = "https://www.koreabaseball.com/Record/Team/Hitter/Basic1.aspx";
//            Connection.Response response = Jsoup.connect(url)
//                    .header("Origin", "https://www.koreabaseball.com")
//                    .header("Referer", "https://www.koreabaseball.com/Record/Team/Hitter/Basic1.aspx")
//                    .data("ctl00$ctl00$ctl00$cphContents$cphContents$cphContents$ddlSeason$ddlSeason", "2001")
//                    .method(Connection.Method.POST)
//                    .execute();
//
//            // 페이지 파싱
//            Document document = response.parse();
//
//            // 원하는 데이터 추출
//            Elements tables = document.select("table.tData");
//
//            for (Element table : tables) {
//                Elements rows = table.select("tbody tr");
//                for (Element row : rows) {
//                    Elements cells = row.select("td");
//                    System.out.println("cells 값은?" +cells);
//                    String avgValue = cells.get(2).text(); // AVG 열에 해당하는 인덱스는 2
//                    System.out.println("AVG Value: " + avgValue);
//                }
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//@PostConstruct
//public void createTeam() throws IOException {
//    System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
//
//    // WebDriver 인스턴스 생성
//    WebDriver driver = new ChromeDriver();
//
//    try {
//        // 웹 페이지 열기
//       driver.get("https://www.koreabaseball.com/Record/Team/Hitter/Basic1.aspx");
//
//        // 페이지 로드 대기
//        new WebDriverWait(driver, 20).until(webDriver ->
//                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
//
//
//
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0, 500)");
//
//        // 년도 변경
//        WebElement yearDropdown = new WebDriverWait(driver, 20)
//                .until(ExpectedConditions.elementToBeClickable(By.id("ctl00_ctl00_ctl00_cphContents_cphContents_cphContents_ddlSeason_ddlSeason")));
//
//        JavascriptExecutor executor = (JavascriptExecutor) driver;
//        executor.executeScript("arguments[0].click();", yearDropdown);
//
//        // Dropdown 클릭
//        yearDropdown.click();
//
//
//
//        // 페이지 소스 가져오기
//        String pageSource = driver.getPageSource();
//        System.out.println("page 값은?" + pageSource);
//
//        // 페이지 소스를 Jsoup으로 파싱하거나 필요한 정보를 추출
//        Document document = Jsoup.parse(pageSource);
//        Elements tables = document.select("table.tData");
//
//        for (Element table : tables) {
//            Elements rows = table.select("tbody tr"); // tbody 내의 각 행을 선택
//            for (Element row : rows) {
//                Elements cells = row.select("td");
//                System.out.println("cells" + cells);
//
//                String avgValue = cells.get(2).text(); // AVG 열에 해당하는 인덱스는 2
//                System.out.println("AVG Value: " + avgValue);
//            }
//        }
//    } finally {
//        // 브라우저 닫기
//        driver.quit();
//    }
//}
}
