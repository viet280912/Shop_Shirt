FROM maven:3-openjdk-17 AS build
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/FirstProject.jar FirstProject.jar
EXPOSE 6868
ENTRYPOINT ["java", "-jar", "FirstProject.jar"]
