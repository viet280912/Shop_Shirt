FROM maven:3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /out/artifacts/FirstProject_jar/FirstProject.jar FirstProject.jar
EXPOSE 6868

ENTRYPOINT ["java", "-jar", "FirstProject.jar"]
