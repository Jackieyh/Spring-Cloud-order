package com.connie.order.service;

import com.connie.order.dto.OrderDTO;

/**
 * 接口类
 *
 * @Author:Jackie
 * @Date:Created in 2018-10-22 17:01:33
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 完结订单(只能卖家操作)
     *
     * @param orderId
     * @return
     */
    OrderDTO finish(String orderId);

}
