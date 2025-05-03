# 使用 OpenJDK 作為基礎映像
FROM eclipse-temurin:21-jdk

# 建立應用資料夾
WORKDIR /app

# 複製打包後的 jar 檔進容器
COPY target/narrative-0.0.1-SNAPSHOT.jar app.jar

# 啟動 Spring Boot 應用
ENTRYPOINT ["java", "-jar", "app.jar"]