# 使用 Maven 3.8.4 和 OpenJDK 17 作為基礎映像
FROM maven:3.8.4-openjdk-17 AS build

# 設定工作目錄
WORKDIR /app

# 複製 pom.xml 和源代碼
COPY pom.xml .
COPY src ./src

# 執行 Maven 構建
RUN mvn clean package -DskipTests

# 使用 OpenJDK 17 作為運行時基礎映像
FROM openjdk:17-jdk-slim

# 設定工作目錄
WORKDIR /Narrative

# 從構建階段複製生成的 JAR 文件到運行時映像
COPY --from=build /app/target/narrative.jar /Narrative/narrative.jar

# 暴露端口 8080
# EXPOSE 8080

# 設定容器啟動時執行的命令
ENTRYPOINT ["java", "-jar", "/Narrative/narrative.jar"]