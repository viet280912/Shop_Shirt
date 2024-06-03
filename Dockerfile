FROM maven:3-openjdk-17 AS build
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim

# Tạo một thư mục /app trong container để chứa file JAR
WORKDIR /app

# Sao chép file JAR từ thư mục build context vào thư mục /app trong container
COPY target/Shop_Shirt-0.0.1-SNAPSHOT.jar .

# Expose cổng mà ứng dụng sẽ chạy trên
EXPOSE 6868

# Chạy ứng dụng Spring Boot khi container được khởi động
ENTRYPOINT ["java", "-jar", "Shop_Shirt-0.0.1-SNAPSHOT.jar"]