FROM openjdk:16-jdk
# 인자 정리
ARG JAR_FILE=corespringsecurity.jar

# 앞에는 HOST OS의 현재 폴더를 의미
# 뒤에는 컨테이너의 현재 폴더(WORKDIR)를 의미
COPY ${JAR_FILE} kbo.jar

# 안해도 되지만, 하는게 좋습니다.
# 이 컨테이너는 8080 포트를 사용한다는 의미 입니다.
EXPOSE 8080

# docker run 명령에서 실행항 명령어
ENTRYPOINT ["java","-jar","/kbo.jar"]
# java 옵션 처리
# ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/corespringsecurity.jar"]