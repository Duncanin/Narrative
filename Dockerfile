# 第一階段：編譯 Maven 專案
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# 第二階段：運行 Spring Boot 應用
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# 複製編譯後的 jar 檔案
COPY --from=build /app/target/*.jar app.jar

# 設定環境變數以指定 port
ENV PORT=10000

# Render 預設會提供 PORT 變數，Spring Boot 必須從中讀取
EXPOSE 10000

# 啟動 Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]