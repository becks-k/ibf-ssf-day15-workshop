FROM maven:3-eclipse-temurin-21

LABEL APPLICATION="RedisDemo"
LABEL MANAGER="becky"

ARG APP_DIR=/appserver

WORKDIR ${APP_DIR}

COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .
COPY src src
COPY .mvn .mvn

RUN mvn clean package -Dmaven.test.skip=true

ENV SERVER_PORT=8080

EXPOSE ${SERVER_PORT}

# ENV SPRING_DATA_REDIS_HOST=localhost
# ENV SPRING_DATA_REDIS_PORT=6379
# ENV SPRING_DATA_REDIS_USERNAME=NOT_SET
# ENV SPRING_DATA_REDIS_PASSWORD=NOT_SET

ENTRYPOINT java -jar target/day15-0.0.1-SNAPSHOT.jar

