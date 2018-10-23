package com.connie.order.repository;

import com.connie.order.OrderApplicationTests;
import com.connie.order.dataobject.OrderMaster;
import com.connie.order.enums.OrderStatusEnum;
import com.connie.order.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class OrderMasterRepositoryTest extends OrderApplicationTests {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void testSave() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(UUID.randomUUID().toString().replace("-", ""));
        orderMaster.setBuyerAddress("WIT");
        orderMaster.setBuyerName("Jackie");
        orderMaster.setBuyerOpenid("123");
        orderMaster.setBuyerPhone("18086438820");
        orderMaster.setOrderAmount(new BigDecimal(20));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }
}