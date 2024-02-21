package io.security.corespringsecurity.scraping;

import io.security.corespringsecurity.domain.entity.kbo.PlayerRecord;
import io.security.corespringsecurity.domain.entity.kbo.PlayerRecordHitter;
import io.security.corespringsecurity.domain.entity.kbo.Players;
import io.security.corespringsecurity.domain.entity.kbo.Teams;
import io.security.corespringsecurity.domain.entity.kbo.games.*;
import io.security.corespringsecurity.domain.entity.schedule.Location;
import io.security.corespringsecurity.domain.entity.schedule.Schedule;
import io.security.corespringsecurity.service.kbo.*;
import io.security.corespringsecurity.service.kbo.games.GameBoardService;
import io.security.corespringsecurity.service.kbo.games.GameHighlightsService;
import io.security.corespringsecurity.service.kbo.games.HitterStatsService;
import io.security.corespringsecurity.service.kbo.games.PitcherStatsService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class KboScraping {

    @Autowired
    private LocationService locationService;
    @Autowired
    private TeamsService teamsService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private GameBoardService gameBoardService;
    @Autowired
    private GameHighlightsService gameHighlightsService;
    @Autowired
    private HitterStatsService hitterStatsService;
    @Autowired
    private PitcherStatsService pitcherStatsService;


    public void executeScraping() {

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
        monthDropdown.selectByValue("9");

        // 일 선택 (1일)
        WebElement dayToSelect = driver.findElement(By.xpath("//a[text()='9']"));
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

                    System.out.println("Date: " + date);
                    System.out.println("Time: " + time);
                    System.out.println("Location: " + location);
                    System.out.println("Home Team: " + homeTeam);
                    System.out.println("Away Team: " + awayTeam);
                    System.out.println("home: " + homePitcherText + ", away: " + awayPitcherText);

                    Schedule schedule = new Schedule();
                    List<Location> locations = locationService.findByLocation(location);
                    if (locations.isEmpty()) {
                        schedule.setLocation(null);
                    } else {
                        schedule.setLocation(locations.get(0));
                    }

                    List<Teams> HomeTeam = teamsService.findByTeam(homeTeam);

                    if (HomeTeam.isEmpty()) {

                    } else {
                        schedule.setHomeTeam(HomeTeam.get(0));
                    }

                    List<Teams> AwayTeam = teamsService.findByTeam(awayTeam);

                    if (AwayTeam.isEmpty()) {

                    } else {
                        schedule.setAwayTeam(AwayTeam.get(0));
                    }


                    schedule.setDate(date);
                    schedule.setTime(time);

                    scheduleService.save(schedule);
                } else {
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

                    Schedule schedule = new Schedule();
                    List<Location> locations = locationService.findByLocation(location);
                    if (locations.isEmpty()) {
                        schedule.setLocation(null);
                    } else {
                        schedule.setLocation(locations.get(0));
                    }

                    List<Teams> HomeTeam = teamsService.findByTeam(homeTeam);

                    if (HomeTeam.isEmpty()) {

                    } else {
                        schedule.setHomeTeam(HomeTeam.get(0));
                    }

                    List<Teams> AwayTeam = teamsService.findByTeam(awayTeam);

                    if (AwayTeam.isEmpty()) {

                    } else {
                        schedule.setAwayTeam(AwayTeam.get(0));
                    }


                    schedule.setDate(date);
                    schedule.setTime(time);

                    scheduleService.save(schedule);
                }

                // 추가 정보 출력...
            }
            System.out.println("-----------------------------------");


            List<WebElement> gameContainerss = driver.findElements(By.cssSelector("ul.game-list-n > li.game-cont"));

            for (WebElement gameContainer : gameContainerss) {
                WebElement statusElement = gameContainer.findElement(By.cssSelector("p.staus"));
                String date = gameContainer.getAttribute("g_dt");

                String statusText = statusElement.getText();

                if (statusText.equals("경기취소")) {

                } else {
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
                    collectAndPrintGameInfo(driver, date);
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


    private void collectAndPrintGameInfo(WebDriver driver, String date) {
        // 각 게임 컨테이너에서 정보 추출

        String stadium = driver.findElement(By.id("txtStadium")).getText().split(" : ")[1];
        String crowd = driver.findElement(By.id("txtCrowd")).getText().split(" : ")[1];
        String startTime = driver.findElement(By.id("txtStartTime")).getText().split(" : ")[1];
        String endTime = driver.findElement(By.id("txtEndTime")).getText().split(" : ")[1];
        String runTime = driver.findElement(By.id("txtRunTime")).getText().split(" : ")[1];

        System.out.println("구장: " + stadium);
        System.out.println("관중: " + crowd);
        System.out.println("게임 시작 시간: " + startTime);
        System.out.println("게임 종료 시간: " + endTime);
        System.out.println("경기 시간: " + runTime);
        // 저장값 변경 해야함.
        // ===============================
        List<Location> locations = locationService.findByLocation(stadium);
        if (locations.isEmpty()) {
            System.out.println("location 리스트가 비어 있습니다.");
        } else {
            System.out.println("location 값은?" + locations.get(0));
        }



        // ===============================


        GameBoardEntity gameBoardEntity = new GameBoardEntity();

        gameBoardEntity.setCrowd(crowd);
        gameBoardEntity.setStartTime(startTime);
        gameBoardEntity.setEndTime(endTime);
        gameBoardEntity.setRunTime(runTime);


        // 승 무 패 결과 값
        List<WebElement> resultRows = driver.findElements(By.cssSelector("div.box-score-wrap .tbl-box-score.data1 tbody tr"));

        String awayTeamResult = "";
        String homeTeamResult = "";

        for (int i = 0; i < resultRows.size(); i++) {
            WebElement row = resultRows.get(i);
            String resultClass = row.findElement(By.cssSelector("td")).getAttribute("class");
            String teamInfo = row.findElement(By.cssSelector("th")).getText();

            if (i == 0) { // 첫 번째 행 (Away team)
                awayTeamResult = resultClass + ": " + teamInfo;
            } else if (i == 1) { // 두 번째 행 (Home team)
                homeTeamResult = resultClass + ": " + teamInfo;
            }
        }
        // awayTeamResult , homeTeamResult 저장
        gameBoardEntity.setAwayTeamResult(awayTeamResult);
        gameBoardEntity.setHomeTeamResult(homeTeamResult);


        // 게임 스코어
        WebElement gameScoreTable = driver.findElement(By.id("tblScordboard2"));

        List<WebElement> scoreRows = gameScoreTable.findElements(By.cssSelector("tbody > tr"));
        List<String> awayTeamScores = new ArrayList<>();
        List<String> homeTeamScores = new ArrayList<>();

// 첫 번째 순회에서 어웨이 팀 점수 수집
        List<WebElement> awayScores = scoreRows.get(0).findElements(By.tagName("td"));
        for (WebElement score : awayScores) {
            awayTeamScores.add(score.getText());
        }

// 두 번째 순회에서 홈 팀 점수 수집
        List<WebElement> homeScores = scoreRows.get(1).findElements(By.tagName("td"));
        for (WebElement score : homeScores) {
            homeTeamScores.add(score.getText());
        }

// InningScores 객체 생성 및 추가
        List<InningScore> inningScores = new ArrayList<>();
        for (int i = 0; i < Math.max(awayTeamScores.size(), homeTeamScores.size()); i++) {
            String awayScore = i < awayTeamScores.size() ? awayTeamScores.get(i) : "-";
            String homeScore = i < homeTeamScores.size() ? homeTeamScores.get(i) : "-";

            InningScore inningScore = new InningScore(i + 1, homeScore, awayScore);
            inningScores.add(inningScore); // 이닝 점수 리스트에 추가
        }

        gameBoardEntity.setInningScores(inningScores);

        // R, H, E, B 데이터
        WebElement rHEBTable = driver.findElement(By.id("tblScordboard3"));
        List<WebElement> rHEBRows = rHEBTable.findElements(By.tagName("tr"));

        int homeTeamR = 0, homeTeamH = 0, homeTeamE = 0, homeTeamB = 0;
        int awayTeamR = 0, awayTeamH = 0, awayTeamE = 0, awayTeamB = 0;

        for (int i = 0; i < rHEBRows.size(); i++) {
            List<WebElement> details = rHEBRows.get(i).findElements(By.tagName("td"));

            // 첫 번째 행 (어웨이)
            if (i == 1 && details.size() >= 4) {
                awayTeamR = Integer.parseInt(details.get(0).getText());
                awayTeamH = Integer.parseInt(details.get(1).getText());
                awayTeamE = Integer.parseInt(details.get(2).getText());
                awayTeamB = Integer.parseInt(details.get(3).getText());

            }
            // 두 번째 행 (홈)
            else if (i == 2 && details.size() >= 4) {
                homeTeamR = Integer.parseInt(details.get(0).getText());
                homeTeamH = Integer.parseInt(details.get(1).getText());
                homeTeamE = Integer.parseInt(details.get(2).getText());
                homeTeamB = Integer.parseInt(details.get(3).getText());
            }
        }

        gameBoardEntity.setAwayTeamB(awayTeamB);
        gameBoardEntity.setAwayTeamE(awayTeamE);
        gameBoardEntity.setAwayTeamH(awayTeamH);
        gameBoardEntity.setAwayTeamR(awayTeamR);

        gameBoardEntity.setHomeTeamB(homeTeamB);
        gameBoardEntity.setHomeTeamE(homeTeamE);
        gameBoardEntity.setHomeTeamH(homeTeamH);
        gameBoardEntity.setHomeTeamR(homeTeamR);
        System.out.println("-----------------------------------");

        System.out.println("우리 팀 R: " + homeTeamR + ", H: " + homeTeamH + ", E: " + homeTeamE + ", B: " + homeTeamB);
        System.out.println("적 팀 R: " + awayTeamR + ", H: " + awayTeamH + ", E: " + awayTeamE + ", B: " + awayTeamB);

        System.out.println("-----------------------------------");
        // SAVE: gameboardentity
        GameBoardEntity savedEntity = gameBoardService.save(gameBoardEntity);
        System.out.println("saveEntity 값은?" + savedEntity.getId());
        System.out.println("date 값은?" + date);
        // 여기까지 나옴

        List<Schedule> scheduleList = scheduleService.findById(locations.get(0) ,date);
        if(scheduleList.isEmpty()){
            System.out.println("schedule 리스트가 비어 있습니다.");
        }
        else{
            System.out.println("scheduleList 값은?" + scheduleList.get(0).getId());
            scheduleList.get(0).setGameBoard(savedEntity);
            scheduleService.save(scheduleList.get(0));
        }

        WebElement gameDetailsTable = driver.findElement(By.id("tblEtc"));
        List<WebElement> detailRows = gameDetailsTable.findElements(By.tagName("tr"));

        GameHighlights gameHighlights = new GameHighlights();
        // GameHighlights
        for (WebElement row : detailRows) {
            // 각 행(TableRow)에서 <th>와 <td> 태그를 찾아 그 텍스트를 출력
            WebElement header = row.findElement(By.tagName("th"));
            WebElement data = row.findElement(By.tagName("td"));

            System.out.println(header.getText() + ": " + data.getText() + ",");
            gameHighlights.matchAndSetData(header.getText(), data.getText());
        }
            gameHighlights.setGameBoard(savedEntity);
            gameHighlightsService.save(gameHighlights);

            System.out.println("-----------------------------------");

        WebElement hitterTable1 = driver.findElement(By.id("tblAwayHitter1"));
        List<WebElement> hitterRows1 = hitterTable1.findElements(By.tagName("tr"));

        Map<Integer, HitterStats> indexToHitterMap = new HashMap<>();

        for (int i = 1; i < hitterRows1.size() -1; i++) {
            WebElement row = hitterRows1.get(i); // 현재 행을 가져옴
            List<WebElement> cells = row.findElements(By.xpath("./*"));
            if (cells.size() > 2) { // 최소 3개의 셀이 있는지 확인
                System.out.println(cells.get(0).getText() + cells.get(1).getText() + cells.get(2).getText());
                HitterStats hitterStats = new HitterStats();

                hitterStats.setGameBoard(savedEntity); // GameBoardEntity 인스턴스 저장

                // 첫 번째 셀(타순)을 기준으로 battingOrder 설정
                String battingOrder = (cells.get(0).getText().trim()); // 공백 제거 후 파싱

                // 두 번째 값(수비 위치)부터 추출 시작
                String defensivePosition = cells.get(1).getText().trim(); // 수비 위치
                String playerName = cells.get(2).getText().trim(); // 선수 이름

                // HitterStats 객체에 값 설정
                hitterStats.setBattingOrder(battingOrder);
                hitterStats.setDefensivePosition(defensivePosition);
                hitterStats.setPlayerName(playerName);
                hitterStats.setTeamType("away");

                // HitterStats 객체를 리스트에 추가
                indexToHitterMap.put(i,hitterStats);
            }
        }
        // tblAwayHitter2에서 선수 상세 타격 정보 추출
        WebElement hitterTable2 = driver.findElement(By.id("tblAwayHitter2"));
        List<WebElement> hitterRows2 = hitterTable2.findElements(By.tagName("tr"));

        for (int rowIndex = 0; rowIndex < hitterRows2.size(); rowIndex++) {
            WebElement row = hitterRows2.get(rowIndex);
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (!cells.isEmpty()) {
                // Map에서 현재 rowIndex에 해당하는 HitterStats 객체를 가져옴
                HitterStats hitter = indexToHitterMap.get(rowIndex);
                if (hitter != null) { // Map에서 객체를 성공적으로 가져온 경우
                    List<InningHitterResult> inningResults = new ArrayList<>();
                    for (int cellIndex = 1; cellIndex < cells.size(); cellIndex++) {
                        // 이닝 결과를 출력하고 InningHitterResult 객체 생성
//                        System.out.println("이닝 " + cellIndex + " 결과: " + cells.get(cellIndex).getText());
                        InningHitterResult inningResult = new InningHitterResult(cellIndex, cells.get(cellIndex).getText());
                        inningResults.add(inningResult);
                    }
                    // HitterStats 객체에 이닝별 결과 리스트 설정
                    hitter.setInningHitterResults(inningResults);
                }
            }
        }

        // tblAwayHitter3에서 선수 종합 타격 정보 추출
        WebElement hitterTable3 = driver.findElement(By.id("tblAwayHitter3"));
        List<WebElement> hitterRows3 = hitterTable3.findElements(By.tagName("tr"));


        for (int rowIndex = 0; rowIndex < hitterRows3.size(); rowIndex++) {
            WebElement row = hitterRows3.get(rowIndex); // 현재 행을 가져옵니다.
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (!cells.isEmpty()) {
                // indexToHitterMap에서 현재 행의 인덱스에 해당하는 HitterStats 객체를 가져옵니다.
                HitterStats hitter = indexToHitterMap.get(rowIndex); // rowIndex를 사용
                if (hitter != null) { // HitterStats 객체가 존재하는 경우
                    // 종합 타격 정보를 HitterStats 객체에 설정합니다.
                    hitter.setAtBats(Integer.parseInt(cells.get(0).getText()));
                    hitter.setHits(Integer.parseInt(cells.get(1).getText()));
                    hitter.setRunsBattedIn(Integer.parseInt(cells.get(2).getText()));
                    hitter.setRuns(Integer.parseInt(cells.get(3).getText()));
                    hitter.setBattingAverage(Double.parseDouble(cells.get(4).getText()));

                }
            }
        }
        System.out.println("-----------------------------------");

        for (Map.Entry<Integer, HitterStats> entry : indexToHitterMap.entrySet()) {
            HitterStats hitter = entry.getValue();
            hitterStatsService.save(hitter);
            // ------------------------------ hitter 개인 데이터 넣기 -----------------------
        }
        // 초기화
        indexToHitterMap.clear();

        WebElement hitterTable11 = driver.findElement(By.id("tblHomeHitter1"));
        List<WebElement> hitterRows11 = hitterTable11.findElements(By.tagName("tr"));

        for (int i = 1; i < hitterRows11.size() -1; i++) {
            WebElement row = hitterRows11.get(i); // 현재 행을 가져옴
            List<WebElement> cells = row.findElements(By.xpath("./*"));
            if (cells.size() > 2) { // 최소 3개의 셀이 있는지 확인
                System.out.println(cells.get(0).getText() + cells.get(1).getText() + cells.get(2).getText());
                HitterStats hitterStats = new HitterStats();

                hitterStats.setGameBoard(savedEntity); // GameBoardEntity 인스턴스 저장

                // 첫 번째 셀(타순)을 기준으로 battingOrder 설정
                String battingOrder = (cells.get(0).getText().trim()); // 공백 제거 후 파싱

                // 두 번째 값(수비 위치)부터 추출 시작
                String defensivePosition = cells.get(1).getText().trim(); // 수비 위치
                String playerName = cells.get(2).getText().trim(); // 선수 이름

                // HitterStats 객체에 값 설정
                hitterStats.setBattingOrder(battingOrder);
                hitterStats.setDefensivePosition(defensivePosition);
                hitterStats.setPlayerName(playerName);
                hitterStats.setTeamType("home");

                // HitterStats 객체를 리스트에 추가
                indexToHitterMap.put(i,hitterStats);
            }
        }

        // tblHomeHitter2에서 선수 상세 타격 정보 추출
        WebElement hitterTable22 = driver.findElement(By.id("tblHomeHitter2"));
        List<WebElement> hitterRows22 = hitterTable22.findElements(By.tagName("tr"));

        for (int rowIndex = 0; rowIndex < hitterRows22.size(); rowIndex++) {
            WebElement row = hitterRows22.get(rowIndex);
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (!cells.isEmpty()) {
                // Map에서 현재 rowIndex에 해당하는 HitterStats 객체를 가져옴
                HitterStats hitter = indexToHitterMap.get(rowIndex);
                if (hitter != null) { // Map에서 객체를 성공적으로 가져온 경우
                    List<InningHitterResult> inningResults = new ArrayList<>();
                    for (int cellIndex = 1; cellIndex < cells.size(); cellIndex++) {
                        // 이닝 결과를 출력하고 InningHitterResult 객체 생성
//                        System.out.println("이닝 " + cellIndex + " 결과: " + cells.get(cellIndex).getText());
                        InningHitterResult inningResult = new InningHitterResult(cellIndex, cells.get(cellIndex).getText());
                        inningResults.add(inningResult);
                    }
                    // HitterStats 객체에 이닝별 결과 리스트 설정
                    hitter.setInningHitterResults(inningResults);
                }
            }
        }

        // tblHomeHitter3에서 선수 종합 타격 정보 추출
        WebElement hitterTable33 = driver.findElement(By.id("tblHomeHitter3"));
        List<WebElement> hitterRows33 = hitterTable33.findElements(By.tagName("tr"));

        for (int rowIndex = 0; rowIndex < hitterRows33.size(); rowIndex++) {
            WebElement row = hitterRows33.get(rowIndex); // 현재 행을 가져옵니다.
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (!cells.isEmpty()) {
                // indexToHitterMap에서 현재 행의 인덱스에 해당하는 HitterStats 객체를 가져옵니다.
                HitterStats hitter = indexToHitterMap.get(rowIndex); // rowIndex를 사용
                if (hitter != null) { // HitterStats 객체가 존재하는 경우
                    // 종합 타격 정보를 HitterStats 객체에 설정합니다.
                    hitter.setAtBats(Integer.parseInt(cells.get(0).getText()));
                    hitter.setHits(Integer.parseInt(cells.get(1).getText()));
                    hitter.setRunsBattedIn(Integer.parseInt(cells.get(2).getText()));
                    hitter.setRuns(Integer.parseInt(cells.get(3).getText()));
                    hitter.setBattingAverage(Double.parseDouble(cells.get(4).getText()));

                }
            }
        }

        for (Map.Entry<Integer, HitterStats> entry : indexToHitterMap.entrySet()) {
            HitterStats hitter = entry.getValue();

            hitterStatsService.save(hitter);
        }

        PitcherStats pitcherStats = new PitcherStats();

        WebElement pitcherTable = driver.findElement(By.id("tblAwayPitcher"));
        List<WebElement> pitcherRows = pitcherTable.findElements(By.tagName("tr"));

        List<PitcherStats> pitcherStatsList = new ArrayList<>();

        for (int rowIndex = 1; rowIndex < pitcherRows.size() - 1; rowIndex++) { // 첫 번째와 마지막 행을 제외
            WebElement row = pitcherRows.get(rowIndex);
            List<WebElement> cells = row.findElements(By.xpath("./*"));
            if (!cells.isEmpty()) {

                PitcherStats pitcher = new PitcherStats();
                pitcher.setGameBoard(savedEntity);
                pitcher.setPlayerName(cells.get(0).getText()); // 선수 이름
                pitcher.setAppearances(cells.get(1).getText()); // 등판
                pitcher.setResult(cells.get(2).getText()); // 결과
                pitcher.setWins(Integer.parseInt(cells.get(3).getText())); // 승
                pitcher.setLosses(Integer.parseInt(cells.get(4).getText())); // 패
                pitcher.setSaves(Integer.parseInt(cells.get(5).getText())); // 세
                pitcher.setInningsPitched(cells.get(6).getText()); // 이닝
                pitcher.setBattersFaced(Integer.parseInt(cells.get(7).getText())); // 타자
                pitcher.setPitchesThrown(Integer.parseInt(cells.get(8).getText())); // 투구수
                pitcher.setAtBats(Integer.parseInt(cells.get(9).getText()));
                pitcher.setHitsAllowed(Integer.parseInt(cells.get(10).getText())); // 피안타
                pitcher.setHomeRunsAllowed(Integer.parseInt(cells.get(11).getText())); // 홈런
                pitcher.setWalks(Integer.parseInt(cells.get(12).getText())); // 4사구
                pitcher.setStrikeouts(Integer.parseInt(cells.get(13).getText())); // 삼진
                pitcher.setEarnedRuns(Integer.parseInt(cells.get(14).getText())); // 실점
                pitcher.setRunsAllowed(Integer.parseInt(cells.get(15).getText())); // 자책
                pitcher.setEarnedRunAverage((cells.get(16).getText())); // 평균자책점
                pitcher.setTeamType("away");
                pitcherStatsList.add(pitcher); // 리스트에 추가
            }
        }

            WebElement pitcherTableHome = driver.findElement(By.id("tblHomePitcher"));
            List<WebElement> pitcherRowsHome = pitcherTableHome.findElements(By.tagName("tr"));

        for (int rowIndex = 1; rowIndex < pitcherRowsHome.size() - 1; rowIndex++) { // 첫 번째와 마지막 행을 제외
            WebElement row = pitcherRowsHome.get(rowIndex);
            List<WebElement> cells = row.findElements(By.xpath("./*"));
            if (!cells.isEmpty()) {

                PitcherStats pitcher = new PitcherStats();
                pitcher.setGameBoard(savedEntity);
                pitcher.setPlayerName(cells.get(0).getText()); // 선수 이름
                pitcher.setAppearances(cells.get(1).getText()); // 등판
                pitcher.setResult(cells.get(2).getText()); // 결과
                pitcher.setWins(Integer.parseInt(cells.get(3).getText())); // 승
                pitcher.setLosses(Integer.parseInt(cells.get(4).getText())); // 패
                pitcher.setSaves(Integer.parseInt(cells.get(5).getText())); // 세
                pitcher.setInningsPitched(cells.get(6).getText()); // 이닝
                pitcher.setBattersFaced(Integer.parseInt(cells.get(7).getText())); // 타자
                pitcher.setPitchesThrown(Integer.parseInt(cells.get(8).getText())); // 투구수
                pitcher.setAtBats(Integer.parseInt(cells.get(9).getText()));
                pitcher.setHitsAllowed(Integer.parseInt(cells.get(10).getText())); // 피안타
                pitcher.setHomeRunsAllowed(Integer.parseInt(cells.get(11).getText())); // 홈런
                pitcher.setWalks(Integer.parseInt(cells.get(12).getText())); // 4사구
                pitcher.setStrikeouts(Integer.parseInt(cells.get(13).getText())); // 삼진
                pitcher.setEarnedRuns(Integer.parseInt(cells.get(14).getText())); // 실점
                pitcher.setRunsAllowed(Integer.parseInt(cells.get(15).getText())); // 자책
                pitcher.setEarnedRunAverage((cells.get(16).getText())); // 평균자책점
                pitcher.setTeamType("home");
                pitcherStatsList.add(pitcher); // 리스트에 추가
            }
        }

        pitcherStatsService.save(pitcherStatsList);

        // 선수들 데이터값에 넣어주기 마지막으로 Pitcher / Hitter 값에
    }
}
