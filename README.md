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
  

- cloud-provider-payment8081, 8082
  - 支付類 ( 生產微服務 )
  - 開放 8081, 8082 port
  

- cloud-consumer-order80
  - 使用者下訂單 ( 消費者微服務 )
  - 開放 80 port
- cloud-eureka-server7001
  - eureka 註冊中心
  - 開放 7001 port
***

### 常用功能
- 心跳檢查
```
http://127.0.0.1:8001/actuator/health
```
  - 返回結果範例
```
{"status":"UP"}
```

***

### 錯誤排除

- eureka故障現象
  - eureka 自我保護機制>某時刻某個微服務不可用時(默認90秒 沒有收到心跳反應), eureka不會立刻清理, 防止誤刪除, 依舊會對該服務的信息進行保存
  - 寧可保留錯誤的服務註冊信息, 也不盲目註銷任何可能健康的服務實例
  - 關閉自我保護機制 
    - 關閉自我保護
```
eureka:
  server:
    enable-self-preservation: false # 自我保護模式關閉 就代表服務一斷線就會被剔除服務清單
```

  - 將出現以下信息
```
THE SELF PRESERVATION MODE IS TURNED OFF. THIS MAY NOT PROTECT INSTANCE EXPIRY IN CASE OF NETWORK/OTHER PROBLEMS
```

  - 修改發送心跳時間間隔
```
eureka:
  instance:
    lease-renewal-interval-in-seconds: 1 # 發送心跳的時間間隔(默認30秒)
```

  - 屬於CAP理論的AP分支 
  ```
  https://medium.com/@thegiive/cap-%E7%90%86%E8%AB%96-19cd81c82658
  ```
  



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
- 修改 eureka 上的主機名稱
- 訪問信息帶有 ip 提示
- 修改 payment 8001, 8002 application.yml  將重複的配置提出
- 添加服務發現的controller
- 將 8001, 8002 application.yml 重複的配置移到外面
- 測試與配置自我保護機制( see 7001, 7002, 8001, 8002 application.yml)