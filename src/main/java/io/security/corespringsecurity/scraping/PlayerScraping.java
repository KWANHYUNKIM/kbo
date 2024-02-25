package io.security.corespringsecurity.scraping;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class PlayerScraping {
    public static void main(String[] args) {
        // ChromeDriver 경로 설정
        String chromeDriverPath = "/Users/kwanhyunkim/Downloads/chromedriver-mac-arm64-3/chromedriver";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        // WebDriver 인스턴스 생성
        WebDriver driver = new ChromeDriver();

        try {

            // 웹 페이지로 이동
            driver.get("https://www.koreabaseball.com/Player/Search.aspx");

            WebElement teamSelectElement = driver.findElement(By.id("cphContents_cphContents_cphContents_ddlTeam"));
            Select teamSelect = new Select(teamSelectElement);

            String[] teams = {"LG","KT", "SSG", "NC", "OB", "HT", "LT", "SS", "HH", "WO"};

            for(int i = 0; i < teams.length; i++) {
                teamSelect.selectByValue(teams[i]);


                // 테이블의 각 행 데이터 추출 및 출력
                List<WebElement> playerLinks = driver.findElements(By.xpath("//table[@class='tEx']//tbody/tr/td[2]/a"));
                // 선수 클릭
                for (int j = 0; j <  playerLinks.size(); j++) {

                    teamSelectElement = driver.findElement(By.id("cphContents_cphContents_cphContents_ddlTeam"));
                    teamSelect = new Select(teamSelectElement);
                    teamSelect.selectByValue(teams[i]);
                    playerLinks = driver.findElements(By.xpath("//table[@class='tEx']//tbody/tr/td[2]/a"));


                    // 선수 이름 링크를 찾아 클릭
                    WebElement currentLink = playerLinks.get(j);
                    currentLink.click();

                    String playerName = driver.findElement(By.id("cphContents_cphContents_cphContents_ucPlayerProfile_lblName")).getText();
                    playerName = playerName.isEmpty() ? null : playerName;

                    String backNumber = driver.findElement(By.id("cphContents_cphContents_cphContents_ucPlayerProfile_lblBackNo")).getText();
                    backNumber = backNumber.isEmpty() ? null : backNumber;

                    String birthDate = driver.findElement(By.id("cphContents_cphContents_cphContents_ucPlayerProfile_lblBirthday")).getText();
                    birthDate = birthDate.isEmpty() ? null : birthDate;

                    String position = driver.findElement(By.id("cphContents_cphContents_cphContents_ucPlayerProfile_lblPosition")).getText();
                    position = position.isEmpty() ? null : position;

                    String heightWeight = driver.findElement(By.id("cphContents_cphContents_cphContents_ucPlayerProfile_lblHeightWeight")).getText();
                    heightWeight = heightWeight.isEmpty() ? null : heightWeight;

                    String career = driver.findElement(By.id("cphContents_cphContents_cphContents_ucPlayerProfile_lblCareer")).getText();
                    career = career.isEmpty() ? null : career;

                    String draftInfo = driver.findElement(By.id("cphContents_cphContents_cphContents_ucPlayerProfile_lblDraft")).getText();
                    draftInfo = draftInfo.isEmpty() ? null : draftInfo;

                    WebElement photoElement = driver.findElement(By.id("cphContents_cphContents_cphContents_ucPlayerProfile_imgProfile"));
                    String photoUrl = photoElement.getAttribute("src");
                    if (photoUrl != null && !photoUrl.isEmpty()) {
                        photoUrl = photoUrl.startsWith("http") ? photoUrl : "http:" + photoUrl;
                    } else {
                        photoUrl = null; // src 속성이 비어있거나 없는 경우
                    }

                    System.out.println("선수명: " + (playerName != null ? playerName : "null"));
                    System.out.println("등번호: " + (backNumber != null ? backNumber : "null"));
                    System.out.println("생년월일: " + (birthDate != null ? birthDate : "null"));
                    System.out.println("포지션: " + (position != null ? position : "null"));
                    System.out.println("신장/체중: " + (heightWeight != null ? heightWeight : "null"));
                    System.out.println("경력: " + (career != null ? career : "null"));
                    System.out.println("지명순위: " + (draftInfo != null ? draftInfo : "null"));
                    System.out.println("사진 URL: " + (photoUrl != null ? photoUrl : "null"));

                    String currentUrl = driver.getCurrentUrl();


                    if (currentUrl.contains("Futures")) {
                        // "Futures"가 포함되어 있으면 이전 페이지로 돌아감
                        driver.navigate().back();

                        try {
                            Thread.sleep(5000); // 5000밀리초 = 5초
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    else{

                        try {
                            Thread.sleep(5000); // 5000밀리초 = 5초
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        WebElement batterLink = driver.findElement(By.xpath("//a[contains(@href, 'HitterDetail.aspx')]"));
                        // 타자 클릭
                        batterLink.click();

                        WebElement totalRecordLink = driver.findElement(By.xpath("//a[contains(@href, 'HitterTotal.aspx')]"));
                        // 통산기록 클릭
                        totalRecordLink.click();
                    }
                }
            }
        } finally {
            // WebDriver 종료
            driver.quit();
        }
    }
}
