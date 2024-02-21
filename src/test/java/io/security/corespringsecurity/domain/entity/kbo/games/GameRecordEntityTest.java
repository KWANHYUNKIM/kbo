package io.security.corespringsecurity.domain.entity.kbo.games;

//import io.security.corespringsecurity.repository.kbo.games.GameBoardRepository;
//import io.security.corespringsecurity.repository.kbo.games.GameRecordRepository;
//import io.security.corespringsecurity.service.impl.kbo.games.GameBoardImpl;
//import io.security.corespringsecurity.service.kbo.games.GameBoardService;
//import io.security.corespringsecurity.service.kbo.games.GameRecordService;
//import io.security.corespringsecurity.service.kbo.games.TeamRecordService;
//import lombok.RequiredArgsConstructor;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//
//import java.util.List;
//
//
//@SpringBootTest
//@Transactional
//@RunWith(SpringRunner.class)
//public class GameRecordEntityTest {
//
//    @Autowired
//    GameRecordRepository gameRecordRepository;
//
//
//    @Test
//    public void testGameRecordEntity() {
//        // Given
//        GameRecordEntity gameRecordEntity = new GameRecordEntity();
//
//        gameRecordEntity.setId(0L);
//        gameRecordEntity.setDate("20231113");
//        gameRecordEntity.setStadium("구장");
//        gameRecordEntity.setTotalAudience(23750);
//        gameRecordEntity.setStart("18:29");
//        gameRecordEntity.setEnd("21:25");
//        gameRecordEntity.setTotalTime("2:56");
//        gameRecordEntity.setHomeTeam("LG");
//        gameRecordEntity.setAwayTeam("KT");
//
//        gameRecordEntity.setDecisiveHit("박해민(3회 1사 2,3루서 우익수 2루타)");
//        gameRecordEntity.setDoubleHit("박해민(3회) 문보경(6회)");
//        gameRecordEntity.setError("박병호2(1 3회) 홍창기(1회) 박동원(5회) 조용호(5회)");
//        gameRecordEntity.setStolenBase("박해민2(3 5회) 문성주(4회)");
//        gameRecordEntity.setSteal("홍창기(1회)");
//        gameRecordEntity.setCaughtStealing("김현수(5회) 문성주(6회)");
//        gameRecordEntity.setDoublePlay("박동원(2회)");
//        gameRecordEntity.setWildPitch("켈리2(1 5회) 유영찬(7회)");
//        gameRecordEntity.setUmpire("박종철 박기택 최수원 김병주 전일수 이민호");
//
//
//        System.out.println("gameRecordEntity" + gameRecordEntity);
//        gameRecordRepository.save(gameRecordEntity);
//        // .....
//
//    }

//    @Test
//    public void testGameBoardEntity() {
//
//        TeamRecordEntity homeRecordEntity = new TeamRecordEntity();
//        TeamRecordEntity awayRecordEntity = new TeamRecordEntity();
//        GameBoardEntity gameBoardEntity = new GameBoardEntity();
//
//        homeRecordEntity.setTeamName("LG");
//        homeRecordEntity.setWinLoseDraw("4승 1패 1무");
//        homeRecordEntity.setScore(6);
//        homeRecordEntity.setHits(11);
//        homeRecordEntity.setErrors(2);
//        homeRecordEntity.setFourBalls(1);
//
//        awayRecordEntity.setTeamName("KT");
//        awayRecordEntity.setWinLoseDraw("1승 4패 0무");
//        awayRecordEntity.setScore(2);
//        awayRecordEntity.setHits(7);
//        awayRecordEntity.setErrors(3);
//        awayRecordEntity.setFourBalls(3);
//
//        List<Score> innings = List.of(
//                new Score(0, 0),
//                new Score(0, 0),
//                new Score(0, 3),
//                new Score(0, 0),
//                new Score(1, 2),
//                new Score(0, 1),
//                new Score(1, 0),
//                new Score(0, 0),
//                new Score(0, 0)
//        );
//        gameBoardEntity.setInnings(innings);
//        System.out.println("teamReocrdService" + homeRecordEntity);
//        teamRecordService.save(homeRecordEntity);
//        teamRecordService.save(awayRecordEntity);
//
//        gameBoardEntity.setAwayTeamRecord(awayRecordEntity);
//        gameBoardEntity.setHomeTeamRecord(homeRecordEntity);
//
//        System.out.println("gameBoardEntity" + gameBoardEntity);
//
//        gameBoardService.save(gameBoardEntity);
//
//    }
//}
//    @Test
//    public void testGameBoardEntity() {
//
//
//
//
//}