package com.connie.order.message;

import com.connie.order.dataobject.ProductInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 普通类
 *
 * @Author:Jackie
 * @Date:Created in 2018-10-24 17:01:22
 */
@Component
@Slf4j
public class ProductInfoReceiver {

    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    @Autowired
    StringRedisTemplate redisTemplate;

    // 从rabbitMQ获得消息
    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message) {

        List<ProductInfo> productInfoList = new Gson().fromJson(message, new TypeToken<List<ProductInfo>>() {
        }.getType());

        log.info("从队列【{}】接收到消息:{}", "productInfo", productInfoList);

        for (ProductInfo productInfo : productInfoList) {
            // 存储到redis中
            redisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE, productInfo.getProductId()),
                    String.valueOf(productInfo.getProductStock()));
        }
    }
}
