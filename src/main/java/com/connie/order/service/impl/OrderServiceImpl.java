package com.connie.order.service.impl;

import com.connie.order.dataobject.OrderMaster;
import com.connie.order.dto.OrderDTO;
import com.connie.order.enums.OrderStatusEnum;
import com.connie.order.enums.PayStatusEnum;
import com.connie.order.repository.OrderDetailRepository;
import com.connie.order.repository.OrderMasterRepository;
import com.connie.order.service.OrderService;
import com.connie.order.utils.KeyUtil;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 普通类
 *
 * @Author:Jackie
 * @Date:Created in 2018-10-22 17:05:44
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        // Todo 2.查询商品信息(调用商品服务)
        // Todo 3.计算总价
        // Todo 4.扣库存(调用商品服务)
        // 5.订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(KeyUtil.genUniqueKey());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(50));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
