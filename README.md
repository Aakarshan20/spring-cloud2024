# spring-cloud
***
### 步驟
***
1. 建module
2. 改pom
3. 寫yml
4. 主啟動
5. 業務類
6. 測試
***
### modules
***
- cloud-api-commons
  - 共用工具類與實體類 ( entities )
  - 不對外開放
  

- cloud-provider-payment8081
  - 支付類 ( 生產微服務 )
  - 開放 8081 port
  

- cloud-consumer-order80
  - 使用者下訂單 ( 消費者微服務 )
  - 開放 80 port
- cloud-eureka-server7001
  - eureka 註冊中心
  - 開放 7001 port
***
### 更新日誌
***
- 添加父類
- 添加支付類 module
***
- db連線設定
- 通用返回接口
- 實體類
- 控制器
- 查詢數據庫成功
- Dao(interface)
- mapper.xml(對應dao)
- service(interface 調用Dao)
- serviceImpl(實現service)
- table.sql
*** 
- 修正mybatis連線問題
*** 
- 添加熱加載
*** 
- 添加 Order module 模擬客戶端 充當spring cloud 的消費者
- *** 
- 添加 Eureka 服務註冊中心