-- INSERT INTO game_record_entity (date, stadium, total_audience, start, end, total_time, home_team, away_team, decisive_hit, double_hit, error, stolen_base, steal, caught_stealing, double_play, wild_pitch, umpire)
-- VALUES ( '20231113', '구장', 23750, '18:29', '21:25', '2:56', 'LG', 'KT', '박해민(3회 1사 2,3루서 우익수 2루타)', '박해민(3회) 문보경(6회)', '박병호2(1 3회) 홍창기(1회) 박동원(5회) 조용호(5회)', '박해민2(3 5회) 문성주(4회)', '홍창기(1회)', '김현수(5회) 문성주(6회)', '박동원(2회)', '켈리2(1 5회) 유영찬(7회)', '박종철 박기택 최수원 김병주 전일수 이민호');


// init : 게임 스케줄을 만든다.
-- INSERT INTO game_record_entity (date, stadium, home_team, away_team)
-- VALUES ( '20231113', '잠실','LG', 'KT');

// init: 각각의 팀을 설정한다.
-- INSERT INTO Team_Record_Entity (ERRORS, FOUR_BALLS, HITS, SCORE, TEAM_NAME, TOTAL_SCORES, WIN_LOSE_DRAW, GAME_RECORD_ID)
-- VALUES (0, 0, 0, 0, 'LG', 0, '0승 0패 0무', 1);

// init: 각각의 팀을 설정한다.
-- INSERT INTO Team_Record_Entity (ERRORS, FOUR_BALLS, HITS, SCORE, TEAM_NAME, TOTAL_SCORES, WIN_LOSE_DRAW, GAME_RECORD_ID)
-- VALUES (0, 0, 0, 0, 'KT', 0, '0승 0패 0무', 1);

-- // init : 각각 값을 board값에 넣어준다.
-- INSERT INTO Game_Board_Entity (home_team_id, away_team_id, game_record_id)
-- VALUES (1, 2, 1);
-- // init : 이닝 초기화
-- INSERT INTO game_board_innings (game_board_id, inning_number, home_scores, away_scores)
-- VALUES
--     (1, 1, 0, 0),
--     (1, 2, 0, 0),
--     (1, 3, 0, 0),
--     (1, 4, 0, 0),
--     (1, 5, 0, 0),
--     (1, 6, 0, 0),
--     (1, 7, 0, 0),
--     (1, 8, 0, 0),
--     (1, 9, 0, 0),
--     (1, 10, 0, 0),
--     (1, 11, 0, 0),
--     (1, 12, 0, 0);
-- // init : 선수 초기화
-- INSERT INTO HITTER_RECORD_ENTITY (ID,GAME_RECORD_ID,TEAM_RECORD_ID)
-- VALUES (1, 1, 1);
--
-- INSERT INTO HITTER_RECORD_ENTITY (ID,GAME_RECORD_ID,TEAM_RECORD_ID)
-- VALUES (1, 1, 2);


-- INSERT INTO HITTER_RECORD_ENTITY_INNING_RECORDS  (HITTER_RECORD_ENTITY_ID, AT_BATS, BATTING_AVERAGE, HITS, JERSEY_NUMBER, PLAYER_NAME, RECORDS, RUNS_BATTED_IN, RUNS_SCORED)
-- VALUES
-- -- 노수광 선수
-- (1,3, 0.000, 0, 1, '노수광', '삼진 1땅', 0, 1),
-- -- 정은원 선수
-- (1,5, 0.400, 2, 2, '정은원', '2안 우비 중비 3땅 좌중안', 0, 0);
--
-- INSERT INTO HITTER_RECORD_ENTITY_INNING_RECORDS (HITTER_RECORD_ENTITY_ID, AT_BATS, BATTING_AVERAGE, HITS, JERSEY_NUMBER, PLAYER_NAME, RECORDS, RUNS_BATTED_IN, RUNS_SCORED)
-- VALUES
--     (2 , 5, 0.400, 2, 1, '이용규', '좌안 좌안 삼진 1비 유땅', 0, 1);
-- -- 김혜성 선수 데이터
--     INSERT INTO HITTER_RECORD_ENTITY_INNING_RECORDS (HITTER_RECORD_ENTITY_ID, AT_BATS, BATTING_AVERAGE, HITS, JERSEY_NUMBER, PLAYER_NAME, RECORDS, RUNS_BATTED_IN, RUNS_SCORED)
-- VALUES
--     (2,5, 0.600, 3, 2, '김혜성', '중비 우안 좌중안 1땅 좌2', 0, 1);

// init : 선수 초기화
INSERT INTO PITCHER_RECORD_ENTITY (ID,GAME_RECORD_ID,TEAM_RECORD_ID)
VALUES (1, 1, 1);

INSERT INTO PITCHER_RECORD_ENTITY (ID,GAME_RECORD_ID,TEAM_RECORD_ID)
VALUES (2, 1, 2);


-- INSERT INTO Game_Board_Entity (HOME_TEAM_ID, AWAY_TEAM_ID, GAME_RECORD_ID)
-- VALUES (
--            -- Score 리스트 (예시 값)
--
--
--            -- homeTeamRecord (예시 값)
--            (SELECT id FROM Team_Record_Entity WHERE teamName = 'LG' AND winLoseDraw = '4승 1패 0무' AND score = 6 AND hits = 11 AND errors = 2 AND fourBalls = 1),
--
--            -- awayTeamRecord (예시 값)
--            (SELECT id FROM Team_Record_Entity WHERE teamName = 'KT' AND winLoseDraw = '1승 4패 0무' AND score = 2 AND hits = 7 AND errors = 3 AND fourBalls = 3),
--
--            -- gameRecord (예시 값)
--            (SELECT id FROM Game_Record_Entity WHERE date = '20231113' AND stadium = '구장' AND totalAudience = 23750 AND start = '18:29' AND end = '21:25' AND totalTime = '2:56' AND homeTeam = 'LG' AND awayTeam = 'KT' AND decisiveHit = '박해민(3회 1사 2,3루서 우익수 2루타)' AND doubleHit = '박해민(3회) 문보경(6회)' AND error = '박병호2(1 3회) 홍창기(1회) 박동원(5회) 조용호(5회)' AND stolenBase = '박해민2(3 5회) 문성주(4회)' AND steal = '홍창기(1회)' AND caughtStealing = '김현수(5회) 문성주(6회)' AND doublePlay = '박동원(2회)' AND wildPitch = '켈리2(1 5회) 유영찬(7회)' AND umpire = '박종철 박기택 최수원 김병주 전일수 이민호')
--        );