FROM openjdk:11-jdk

EXPOSE 8080

ARG JAR_FILE=/build/libs/obab-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=local","-Duser.timezone=Asia/Seoul","/app.jar"]
