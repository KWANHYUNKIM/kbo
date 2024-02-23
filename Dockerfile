FROM openjdk:16-jdk
# 인자 정리
ARG JAR_FILE=target/corespringsecurity-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

# 안해도 되지만, 하는게 좋습니다.
# 이 컨테이너는 8080 포트를 사용한다는 의미 입니다.
EXPOSE 8080

# docker run 명령에서 실행항 명령어
ENTRYPOINT ["java","-jar","/app.jar"]
# java 옵션 처리
# ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/app.jar"]

