FROM openjdk:17-jdk-slim

# 設定工作目錄
WORKDIR /Narrative

# 複製 JAR 檔案
COPY target/narrative.jar /Narrative/narrative.jar

# 啟動應用程式
CMD ["java", "-jar", "narrative.jar"]