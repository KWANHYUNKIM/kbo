package io.security.corespringsecurity.service.impl.kbo;

import io.security.corespringsecurity.repository.kbo.crawl.TeamRepository;
import io.security.corespringsecurity.domain.entity.kbo.crawl.Team;
import io.security.corespringsecurity.service.kbo.TeamService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jsoup.select.Elements;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private static String homepage = "https://www.koreabaseball.com/Record/TeamRank/TeamRank.aspx";
    @Autowired
    private final TeamRepository teamRepository;

    @PostConstruct
    public void createTeam() throws IOException {
        teamRepository.deleteAll();

        Document document = Jsoup.connect(homepage).get();

        Elements tables = document.select("table.tData");
        if (tables.size() > 0) {
            for (int i = 0; i <= 108; i += 12) {
                Elements tdElementsInTable = tables.select("td");

                Team team = Team.builder()
                        .ranking(Integer.parseInt(tdElementsInTable.get(i).text())) // 팀 순위
                        .teamName(tdElementsInTable.get(i + 1).text()) // 팀 이름
                        .matches(Integer.parseInt(tdElementsInTable.get(i + 2).text())) // 경기
                        .win(Integer.parseInt(tdElementsInTable.get(i + 3).text())) // 승
                        .loss(Integer.parseInt(tdElementsInTable.get(i + 4).text())) // 패
                        .draw(Integer.parseInt(tdElementsInTable.get(i + 5).text())) // 무
                        .winningPercentage(Double.parseDouble(tdElementsInTable.get(i + 6).text())) // 승률
                        .gameDifference(tdElementsInTable.get(i + 7).text()) // 게임차
                        .recent10Games(tdElementsInTable.get(i + 8).text()) // 최근10경기
                        .streak(tdElementsInTable.get(i + 9).text()) // 연속
                        .home(tdElementsInTable.get(i + 10).text()) // 홈
                        .away(tdElementsInTable.get(i + 11).text()) // 방문
                        .build();

                teamRepository.save(team);
            }
        }
    }

    @Override
    public List<Team> findByTeam(String team) {
        return teamRepository.findByTeam(team);
    }
}
