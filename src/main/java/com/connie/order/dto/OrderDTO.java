package com.connie.order.dto;

import com.connie.order.dataobject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 普通类
 *
 * @Author:Jackie
 * @Date:Created in 2018-10-22 17:02:28
 */
@Data
public class OrderDTO {
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    /**
     * 订单状态，默认0为新订单
     */
    private Integer orderStatus;

    /**
     * 支付状态，默认0为未支付
     */
    private Integer payStatus;

    private List<OrderDetail> orderDetailList;
}
