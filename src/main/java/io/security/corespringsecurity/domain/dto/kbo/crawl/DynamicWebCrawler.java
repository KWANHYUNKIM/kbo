import com.gargoylesoftware.htmlunit.AjaxController;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.interactions.Actions;

public class DynamicWebCrawler {

    public static void main(String[] args) {
        // ChromeDriver 경로 설정
        String chromeDriverPath = "/Users/kwanhyunkim/Downloads/chromedriver-mac-arm64/chromedriver";

        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        WebDriver driver = new ChromeDriver();


        try {
            // 웹 페이지 열기
            driver.get("https://www.koreabaseball.com/Schedule/GameCenter/Main.aspx#none;");  // 실제 웹 페이지의 URL로 변경

            // <li class="prev"> 요소 찾기
            WebElement prevElement = driver.findElement(By.className("prev"));

            // <a> 요소 찾기
            WebElement linkElement = prevElement.findElement(By.tagName("a"));

            Actions actions = new Actions(driver);

            // 더블 클릭 수행
            try {
                // 더블 클릭 수행
                actions.doubleClick(linkElement).perform();

                // 딜레이 추가
                Thread.sleep(1000); // 1초 딜레이

                // 두 번째 더블 클릭 수행
                actions.doubleClick(linkElement).perform();
            } catch (InterruptedException e) {
                e.printStackTrace();

//            linkElement.click();

//            WebElement bxViewportElement = driver.findElement(By.className("bx-viewport"));
//
//            // 클릭
//            bxViewportElement.click();
//
//            WebElement reviewTabElement = driver.findElement(By.xpath("//li[@class='tab-tit'][@section='REVIEW']"));
//
//            reviewTabElement.click();
//
//            //==================================================================================================================
//
//            // 원하는 작업 수행
//            String pageSource = driver.getPageSource();
////            System.out.println("Web Page HTML:\n" + pageSource);
//
//
//            Document document = Jsoup.parse(pageSource);
//            Element gameCenterContents = document.getElementById("gameCenterContents");
//
////            Elements txtDate = gameCenterContents.select("li.today");
//
//            // txtDate 리스트에서 첫 번째 요소를 가져오고, 그 안에 있는 span 요소를 선택
////            Element dateElement = txtDate.first().select("span#lblGameDate").first();
//
//// span 요소 안의 텍스트 값을 가져오기
////            String dateText = dateElement.text();
//
//// 날짜 출력 또는 처리
//            System.out.println("게임 날짜: " + gameCenterContents);
//
//
//            // 구장 , 관중, 개시, 종료, 경기시간  Todo :  데이터만 추출하면됨.
//            Element txtStadium = gameCenterContents.getElementById("txtStadium");
//            String stadiumName = txtStadium.text().replace("구장 : ", "");
//            Element txtCrowd = gameCenterContents.getElementById("txtCrowd");
//            String CrowdName = txtCrowd.text().replace("관중 : ", "");
//
//            Element txtStartTime = gameCenterContents.getElementById("txtStartTime");
//            String StartTime = txtStartTime.text().replace("개시 : ", "");
//
//            Element txtEndTime = gameCenterContents.getElementById("txtEndTime");
//            String EndTime = txtEndTime.text().replace("종료 :","");
//            Element txtRunTime = gameCenterContents.getElementById("txtRunTime");
//            String RunTime = txtRunTime.text().replace("경기시간 :", "");
//
////            System.out.println(stadiumName + CrowdName + StartTime + EndTime + RunTime); // 구장 : 잠실
////============================================================================================================= 패스
//
//            // 점수표 크롤링 Todo: 세분화 해줘야함. 데이터 : 패 , 승 1회 ~ 9 회 까지 점수표 안타, 득점 에러 등등..
//            Elements boxScore = gameCenterContents.getElementsByClass("box-score-wrap");
//
//
//            // 승 패
//            Elements loseTable = boxScore.select("div.tbl-box-score.data1");
//            Elements win = loseTable.select("td.win");
//            Elements lose = loseTable.select("td.lose");
//
//            System.out.println(lose);
//
//            // 기록 요약 크롤링 Todo : 결승타, 2루타, 실책, 도루 , 도루자, 주루사 , 병살타, 폭투, 심판
//            Element summary = gameCenterContents.getElementById("tblEtc");
//
//            // aways 선수들 기록 : 1 ~ 9 번  9 번 9 번 ( 대타 ) 1번 ~9번 순
//            Element awayHitter = gameCenterContents.getElementById("tblAwayHitter1");
//            // Todo: 문제 : home / aways 둘다 나옴 className 같아서 . 그렇다고 getElementsById로 하면 오류 나오지않음.
//            Elements awayHitter2 = gameCenterContents.getElementsByClass("tbl-type03 data2 mb30");
//            // Todo : 문제 tblAwayHttier2
//            Element awaysHitter2_ =gameCenterContents.getElementById("tblAwayHitter2");
//            // Todo : 문제 home / away 둘다 나옴 className이 같아서
//            Elements awaysHitter3 =gameCenterContents.getElementsByClass("tbl-type03 data3 mb30");
//
//            // Todo : solution : for문으로 돌려서줘서 배열로 넣어줘도 괜찮다.
//            // ===========================================================================
//
//            // TOdo : pitcher 2 팀 다 나온다. 세분화 해야줘야함.
//            Elements pitcherAwaysRecord = gameCenterContents.getElementsByClass("pitcher-record-area");


            }
        }
        finally {
            // WebDriver 종료
            driver.quit();
        }
    }
}