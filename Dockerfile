FROM maven:3-openjdk-17 AS build
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim

COPY  --from=build Shop_Shirt-0.0.1-SNAPSHOT.jar FirstProject.jar

EXPOSE 6868

ENTRYPOINT ["java", "-jar", "FirstProject.jar"]

