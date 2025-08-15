# Run stage: JRE만 포함
FROM eclipse-temurin:17-jre
WORKDIR /app

# JAR 파일 복사
COPY ./*.jar app.jar

ENV SPRING_PROFILES_ACTIVE=prod
ENV SERVER_PORT=8080

EXPOSE ${SERVER_PORT}

ENTRYPOINT ["java", "-Xms256m", "-Xmx512m", "-XX:+UseG1GC", \
            "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", \
            "-Dserver.port=${SERVER_PORT}", "-jar", "app.jar"]