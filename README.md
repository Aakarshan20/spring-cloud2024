# spring-cloud
***
### 步驟

1. 建module
2. 改pom
3. 寫yml
4. 主啟動
5. 業務類
6. 測試
***
### modules

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

- 添加父類
- 添加支付類 module
- db連線設定
- 通用返回接口
- 實體類
- 控制器
- 查詢數據庫成功
- Dao ( interface )
- mapper.xml ( 對應 dao )
- service ( interface 調用 Dao )
- serviceImpl ( 實現 service )
- table.sql
- 修正mybatis連線問題
- 添加熱加載
- 添加 Order module 模擬客戶端 充當spring cloud 的消費者
- 添加 Eureka 服務註冊中心
- 支付 8001 入駐進 eureka server
- 更正支付 module 名稱 8081->8001
- 訂單 80 入駐進 eureka server
- 修改總工程 pom.xml: <module>cloud-provider-payment8081</module> -> <module>cloud-provider-payment8001</module>
- 添加 hosts 映射
- 添加 eureka-server-7001
- 搭建 eureka 集群
- 支付( payment ) ,訂單( order ) 註冊入集群  
- eureka server ( 7001, 7002 ) 開啟自我保護機制
- eureka renewal-percent-threshold 改為0.85
- eureka 開啟自我保護機制
- 添加 payment8002 module, 把支付( payment ) 變成集群
- eureka 關閉自我保護機制
- eureka renewal-percent-threshold: 0.45 
- 增加 payment 8002 module
- 修改 order 80 config: 加上負載均衡機制
- 修改 order 80 controller: 將地址改為微服務代號
- payment 8001, 8002 回傳 port 號, 驗證負載均衡功能