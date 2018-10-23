package com.connie.order.controller;

import com.connie.order.client.ProductClient;
import com.connie.order.dataobject.ProductInfo;
import com.connie.order.dto.CartDTO;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * 普通类
 *
 * @Author:Jackie
 * @Date:Created in 2018-10-23 09:42:38
 */
@RestController
@Slf4j
public class ClientController {

    @Autowired
    private ProductClient productClient;

    @GetMapping("/getProductMsg")
    public String getProductMsg() {

        String response = productClient.productMsg();
        log.info("response={}", response);
        return response;
    }

    @GetMapping("/getProductList")
    public List<ProductInfo> getProductList() {
        List<ProductInfo> list = productClient.listForOrder(Arrays.asList("22f16fcaac86416b8029b4b114677399", "b2d28e2e7737491abecaff584f9fecd9"));
        return list;
    }

    @GetMapping("/productDecreaseStock")
    public String productDecreaseStock() {
        productClient.decreaseStock(Arrays.asList(new CartDTO("uyutyuyt", 100)));
        return "OK";
    }
}
