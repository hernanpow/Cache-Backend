FROM gradle:7.4.2-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/project
WORKDIR /home/gradle/project
RUN gradle build

FROM openjdk:11-jre-slim
COPY --from=build /home/gradle/project/build/libs/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
