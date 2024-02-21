package io.security.corespringsecurity.scraping;

import io.security.corespringsecurity.domain.dto.kbo.games.ScheduleDto;
import io.security.corespringsecurity.domain.dto.kbo.games.TeamsDto;
import io.security.corespringsecurity.domain.entity.kbo.Teams;
import io.security.corespringsecurity.domain.entity.schedule.Location;
import io.security.corespringsecurity.domain.entity.schedule.Schedule;
import io.security.corespringsecurity.service.kbo.LocationService;
import io.security.corespringsecurity.service.kbo.ScheduleService;
import io.security.corespringsecurity.service.kbo.TeamService;
import io.security.corespringsecurity.service.kbo.TeamsService;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
public class
KboSCrapingTest {

    @Autowired
    LocationService locationService;
    @Autowired
    TeamsService teamsService;
    @Autowired
    ScheduleService scheduleService;

    @Test
    public void testCollectAndPrintGameInfo(){
        String chromeDriverPath = "/Users/kwanhyunkim/Downloads/chromedriver-mac-arm64/chromedriver";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.koreabaseball.com/Schedule/GameCenter/Main.aspx");

        WebElement datepickerTrigger = driver.findElement(By.className("ui-datepicker-trigger"));
        datepickerTrigger.click();

        // 검색어 입력 및 검색 실행

        // 년도 선택
        Select yearDropdown = new Select(driver.findElement(By.className("ui-datepicker-year")));
        yearDropdown.selectByValue("2023");

        // 월 선택 (0부터 시작하므로, 3월은 '2'로 선택)
        Select monthDropdown = new Select(driver.findElement(By.className("ui-datepicker-month")));
        monthDropdown.selectByValue("3");

        // 일 선택 (1일)
        WebElement dayToSelect = driver.findElement(By.xpath("//a[text()='1']"));
        dayToSelect.click();

        boolean hasNextDay = true;

        while (hasNextDay) {
            // 현재 페이지에서 모든 게임 컨테이너 찾기
            List<WebElement> gameContainers = driver.findElements(By.cssSelector("ul.game-list-n > li.game-cont"));
            for (WebElement game : gameContainers) {
                WebElement statusElement = game.findElement(By.cssSelector("p.staus"));
                String statusText = statusElement.getText();

                if (statusText.equals("경기취소")) {
                    String date = game.getAttribute("g_dt"); // 게임 날짜
                    String location = game.getAttribute("s_nm"); // 경기 장소
                    String homeTeam = game.getAttribute("home_nm"); // 홈 팀 이름
                    String awayTeam = game.getAttribute("away_nm"); // 어웨이 팀 이름
                    String time = game.findElement(By.xpath(".//div[@class='top']/ul/li[2]")).getText(); // 경기 시간
                    WebElement homePitcherInfo = game.findElement(By.cssSelector(".team.home .today-pitcher"));
                    WebElement awayPitcherInfo = game.findElement(By.cssSelector(".team.away .today-pitcher"));
                    String homePitcherText = homePitcherInfo.getText();
                    String awayPitcherText = awayPitcherInfo.getText();
                    // ===================================================
                    System.out.println("Date: " + date);
                    System.out.println("Time: " + time);
                    System.out.println("Location: " + location);
                    System.out.println("Home Team: " + homeTeam);
                    System.out.println("Away Team: " + awayTeam);
                    System.out.println("home: " + homePitcherText + ", away: " + awayPitcherText);
                    //======================================================

                    System.out.println(homeTeam);
                    System.out.println(awayTeam);

//                    Schedule schedule = new Schedule();
//                    Location locations = locationService.findByLocation(location);
//                    Teams HomeTeam = teamsService.findByTeam(homeTeam);
//                    Teams AwayTeam = teamsService.findByTeam(awayTeam);
//                    schedule.setDate(date);
//                    schedule.setTime(time);
//                    schedule.setLocation(locations);
//                    schedule.setHomeTeam(HomeTeam);
//                    schedule.setAwayTeam(AwayTeam);
//                    scheduleService.save(schedule);
                    //======================================================
                }
                else {
                    String date = game.getAttribute("g_dt"); // 게임 날짜
                    String location = game.getAttribute("s_nm"); // 경기 장소
                    String homeTeam = game.getAttribute("home_nm"); // 홈 팀 이름
                    String awayTeam = game.getAttribute("away_nm"); // 어웨이 팀 이름

                    // 기존 코드에서 추출한 다른 정보들...
                    String time = game.findElement(By.xpath(".//div[@class='top']/ul/li[2]")).getText(); // 경기 시간
                    String homeScore = game.findElement(By.cssSelector(".team.home .score")).getText(); // 홈 팀 점수
                    String awayScore = game.findElement(By.cssSelector(".team.away .score")).getText(); // 어웨이 팀 점수
                    WebElement homePitcherInfo = game.findElement(By.cssSelector(".team.home .today-pitcher"));
                    String homePitcherText = homePitcherInfo.getText();
                    WebElement awayPitcherInfo = game.findElement(By.cssSelector(".team.away .today-pitcher"));
                    String awayPitcherText = awayPitcherInfo.getText();

                    // 나머지 정보 추출 및 출력 코드...
                    System.out.println("Date: " + date);
                    System.out.println("Time: " + time);
                    System.out.println("Location: " + location);
                    System.out.println("Home Team: " + homeTeam);
                    System.out.println("Score: " + homeScore);
                    System.out.println("Away Team: " + awayTeam);
                    System.out.println(" Score: " + awayScore);
                    System.out.println("home: " + homePitcherText + ", away: " + awayPitcherText);

//                    Schedule schedule = new Schedule();
//                    Location locations = locationService.findByLocation(location);
//                    Teams HomeTeam = teamsService.findByTeam(homeTeam);
//                    Teams AwayTeam = teamsService.findByTeam(awayTeam);
//                    schedule.setDate(date);
//                    schedule.setTime(time);
//                    schedule.setLocation(locations);
//                    schedule.setHomeTeam(HomeTeam);
//                    schedule.setAwayTeam(AwayTeam);
//                    scheduleService.save(schedule);

                    // 추가 정보 출력...
                }
                System.out.println("-----------------------------------");
            }


            List<WebElement> gameContainerss = driver.findElements(By.cssSelector("ul.game-list-n > li.game-cont"));

            for (WebElement gameContainer : gameContainerss) {
                WebElement statusElement = gameContainer.findElement(By.cssSelector("p.staus"));
                String statusText = statusElement.getText();
                if(statusText.equals("경기취소")){

                }
                else{
                    gameContainer.click(); // 게임 컨테이너 클릭
                    WebDriverWait wait = new WebDriverWait(driver, 3); // 10초 동안 대기
                    WebElement reviewTab = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.tab-tit[section='REVIEW']")));
                    reviewTab.click();
                    reviewTab.click();


                    try {
                        // 3초간 대기
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // 페이지 내에서 필요한 정보 수집
                    collectAndPrintGameInfo(driver);
                }
            }

            // 다음 날짜로 넘어가기
            WebElement nextButton = driver.findElement(By.id("lnkNext"));
            if (nextButton != null && nextButton.isDisplayed()) {
                nextButton.click();
            } else {
                hasNextDay = false; // 다음 날짜 버튼이 없거나 비활성화된 경우
            }
        }
    }

    private static void collectAndPrintGameInfo(WebDriver driver) {
        // 각 게임 컨테이너에서 정보 추출

        String stadium = driver.findElement(By.id("txtStadium")).getText();
        String crowd = driver.findElement(By.id("txtCrowd")).getText();
        String startTime = driver.findElement(By.id("txtStartTime")).getText();
        String endTime = driver.findElement(By.id("txtEndTime")).getText();
        String runTime = driver.findElement(By.id("txtRunTime")).getText();

        System.out.println("구장: " + stadium);
        System.out.println("관중: " + crowd);
        System.out.println("게임 시작 시간: " + startTime);
        System.out.println("게임 종료 시간: " + endTime);
        System.out.println("경기 시간: " + runTime);

        List<WebElement> resultRows = driver.findElements(By.cssSelector("div.box-score-wrap .tbl-box-score.data1 tbody tr"));
        for (WebElement row : resultRows) {
            String resultClass = row.findElement(By.cssSelector("td")).getAttribute("class");
            String teamInfo = row.findElement(By.cssSelector("th")).getText();
            System.out.println(resultClass + ": " + teamInfo);
        }

        // 게임 스코어
        WebElement gameScoreTable = driver.findElement(By.id("tblScordboard2"));
        List<WebElement> scoreRows = gameScoreTable.findElements(By.tagName("tr"));
        for (WebElement row : scoreRows) {
            List<WebElement> scores = row.findElements(By.tagName("td"));
            for (WebElement score : scores) {
                System.out.print(score.getText() + " ");
            }
            System.out.println(); // 새로운 줄로 구분
        }

        // R, H, E, B 데이터
        WebElement rHEBTable = driver.findElement(By.id("tblScordboard3"));
        List<WebElement> rHEBRows = rHEBTable.findElements(By.tagName("tr"));
        for (WebElement row : rHEBRows) {
            List<WebElement> details = row.findElements(By.tagName("td"));
            for (WebElement detail : details) {
                System.out.print(detail.getText() + " ");
            }
            System.out.println(); // 새로운 줄로 구분
        }

        System.out.println("-----------------------------------");

        WebElement gameDetailsTable = driver.findElement(By.id("tblEtc"));
        List<WebElement> detailRows = gameDetailsTable.findElements(By.tagName("tr"));

        for (WebElement row : detailRows) {
            // 각 행(TableRow)에서 <th>와 <td> 태그를 찾아 그 텍스트를 출력
            WebElement header = row.findElement(By.tagName("th"));
            WebElement data = row.findElement(By.tagName("td"));

            System.out.println(header.getText() + ": " + data.getText());
        }

        System.out.println("-----------------------------------");

        WebElement hitterTable1 = driver.findElement(By.id("tblAwayHitter1"));
        List<WebElement> hitterRows1 = hitterTable1.findElements(By.tagName("tr"));

        System.out.println("롯데 자이언츠 선수 기본 정보:");
        for (WebElement row : hitterRows1) {
            List<WebElement> cells = row.findElements(By.xpath("./*")); // <td>와 <th> 모두를 포함하여 가져옴
            if (cells.size() > 2) { // 최소 3개의 셀이 있는지 확인
                System.out.println(cells.get(0).getText() + " " + cells.get(1).getText() + " " + cells.get(2).getText());
            }
        }

        // tblAwayHitter2에서 선수 상세 타격 정보 추출
        WebElement hitterTable2 = driver.findElement(By.id("tblAwayHitter2"));
        List<WebElement> hitterRows2 = hitterTable2.findElements(By.tagName("tr"));

        System.out.println("롯데 자이언츠 선수 상세 타격 정보:");
        for (WebElement row : hitterRows2) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                System.out.print(cell.getText() + " ");
            }
            System.out.println(); // 각 행의 끝에서 줄바꿈
        }

        // tblAwayHitter3에서 선수 종합 타격 정보 추출
        WebElement hitterTable3 = driver.findElement(By.id("tblAwayHitter3"));
        List<WebElement> hitterRows3 = hitterTable3.findElements(By.tagName("tr"));

        System.out.println("롯데 자이언츠 선수 종합 타격 정보:");
        for (WebElement row : hitterRows3) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                System.out.print(cell.getText() + " ");
            }
            System.out.println(); // 각 행의 끝에서 줄바꿈
        }
        System.out.println("-----------------------------------");

        WebElement hitterTable11 = driver.findElement(By.id("tblHomeHitter1"));
        List<WebElement> hitterRows11 = hitterTable11.findElements(By.tagName("tr"));

        System.out.println("두산 베어스 선수 기본 정보:");
        for (WebElement row : hitterRows11) {
            List<WebElement> cells = row.findElements(By.xpath("./*")); // <td>와 <th> 모두를 포함하여 가져옴
            if (cells.size() > 2) { // 최소 3개의 셀이 있는지 확인
                System.out.println(cells.get(0).getText() + " " + cells.get(1).getText() + " " + cells.get(2).getText());
            }
        }

// tblHomeHitter2에서 선수 상세 타격 정보 추출
        WebElement hitterTable22 = driver.findElement(By.id("tblHomeHitter2"));
        List<WebElement> hitterRows22 = hitterTable22.findElements(By.tagName("tr"));

        System.out.println("두산 베어스 선수 상세 타격 정보:");
        for (WebElement row : hitterRows22) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                System.out.print(cell.getText() + " ");
            }
            System.out.println(); // 각 행의 끝에서 줄바꿈
        }

// tblHomeHitter3에서 선수 종합 타격 정보 추출
        WebElement hitterTable33 = driver.findElement(By.id("tblHomeHitter3"));
        List<WebElement> hitterRows33 = hitterTable33.findElements(By.tagName("tr"));

        System.out.println("두산 베어스 선수 종합 타격 정보:");
        for (WebElement row : hitterRows33) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                System.out.print(cell.getText() + " ");
            }
            System.out.println(); // 각 행의 끝에서 줄바꿈
        }
        System.out.println("-----------------------------------");

        WebElement pitcherTable = driver.findElement(By.id("tblAwayPitcher"));
        List<WebElement> pitcherRows = pitcherTable.findElements(By.tagName("tr"));

        System.out.println("롯데 자이언츠 투수 기록:");
        for (WebElement row : pitcherRows) {
            // Fetch all cells (td or th elements) within the row
            List<WebElement> cells = row.findElements(By.xpath("./*"));

            // Iterate over each cell and print its text content
            for (WebElement cell : cells) {
                System.out.print(cell.getText() + " | ");
            }
            System.out.println(); // Move to the next line after printing all cells in the row
        }

// Print footer (total) row separately if needed
        WebElement footerRow = pitcherTable.findElement(By.tagName("tfoot")).findElement(By.tagName("tr"));
        List<WebElement> footerCells = footerRow.findElements(By.xpath("./*"));
        System.out.print("Total: ");
        for (WebElement cell : footerCells) {
            System.out.print(cell.getText() + " | ");
        }
        System.out.println(); // Move to the next line after printing the footer row

        WebElement pitcherTableHome = driver.findElement(By.id("tblHomePitcher"));
        List<WebElement> pitcherRowsHome = pitcherTableHome.findElements(By.tagName("tr"));

        System.out.println("두산 베어스 투수 기록:");
        for (WebElement row : pitcherRowsHome) {
            // Fetch all cells (td or th elements) within the row
            List<WebElement> cells = row.findElements(By.xpath("./*"));

            // Iterate over each cell and print its text content
            for (WebElement cell : cells) {
                System.out.print(cell.getText() + " | ");
            }
            System.out.println(); // Move to the next line after printing all cells in the row
        }

        // Print footer (total) row separately if needed
        WebElement footerRowHome = pitcherTableHome.findElement(By.tagName("tfoot")).findElement(By.tagName("tr"));
        List<WebElement> footerCellsHome = footerRowHome.findElements(By.xpath("./*"));
        System.out.print("Total: ");
        for (WebElement cell : footerCellsHome) {
            System.out.print(cell.getText() + " | ");
        }
        System.out.println(); // Move to the next line after printing the footer row

    }

}



