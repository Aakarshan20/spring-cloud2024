package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@MapperScan("com.atguigu.springcloud.dao")
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value="/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result =  paymentService.create(payment);
        log.info("****插入結果****" + result);

        if(result > 0 ){
            return new CommonResult(200, "插入數據庫成功, server port:" + serverPort, result);
        } else {
            return new CommonResult(444, "插入數據庫失敗");
        }

    }

    @GetMapping(value="/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment =  paymentService.getPaymentById(id);
        //Payment payment = null;
        log.info("****查詢結果****" + payment);

        if(payment != null ){
            return new CommonResult(200, "查詢成功 server port:" + serverPort, payment);
        } else {
            return new CommonResult(444, "查詢失敗, server port: "+serverPort + ", id:" + id);
        }

    }

    @GetMapping(value="/payment/discovery")
    public Object discovery(){ // 服務發現
        List<String> services = discoveryClient.getServices(); // 取得已註冊的服務列表

        // 預期會有cloud-payment-service 與 cloud-order-service
        for(String element: services){
            log.info("***** element: " + element);
        }

        // 取出名為 CLOUD-PAYMENT-SERVICE 的實例
        // 預期會有 CLOUD-PAYMENT-SERVICE	192.168.18.5	8002	http://192.168.18.5:8002
        // 與      CLOUD-PAYMENT-SERVICE	192.168.18.5	8001	http://192.168.18.5:8001
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance instance: instances){
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort()  + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value="/test")
    public String test(){
        return "test";
    }

}
