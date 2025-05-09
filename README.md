# 讀書會報名系統（Narrative）

本專案為一個以 Java Spring Boot 架構開發的讀書會報名平台，提供使用者瀏覽讀書會資訊、填寫報名表單，並可由後台管理者進行報名資料查詢與審核。此為我在自學程式過程中所開發的實作作品。

## 🔧 技術架構

- 後端：Spring Boot、Spring MVC、Spring Data JPA
- 前端：HTML5、CSS3、Bootstrap（學習中）
- 資料庫：MySQL
- 模板引擎：Thymeleaf
- 開發工具：IntelliJ IDEA、Visual Studio Code
- 版本控制：Git / GitHub

## 💡 功能說明

### 使用者端功能
- 瀏覽所有開放中的讀書會場次
- 點擊進入場次詳情頁查看說明與剩餘名額
- 填寫並送出報名表單（姓名、Email、電話等資訊）
- 報名成功畫面提示

### 管理者端功能
- 登入後查看所有報名紀錄
- 依讀書會場次篩選報名資料
- 查看個別報名者詳細資料（含備註與歷史紀錄）
- 管理讀書會資訊、書籍與卡片媒材（開發中）
