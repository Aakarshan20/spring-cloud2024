package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@MapperScan("com.atguigu.springcloud.dao")
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping(value="/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result =  paymentService.create(payment);
        log.info("****插入結果****" + result);

        if(result > 0 ){
            return new CommonResult(200, "插入數據庫成功", result);
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
            return new CommonResult(200, "查詢成功", payment);
        } else {
            return new CommonResult(444, "查詢失敗, id:" + id);
        }

    }
    @GetMapping(value="/test")
    public String test(){
        return "test";
    }

}
