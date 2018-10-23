package com.connie.order.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 普通类
 *
 * @Author:Jackie
 * @Date:Created in 2018-10-22 16:32:45
 */
@Table(name = "order_master")
@Data   //lombok注解,省去get/set
@Entity //类与数据库表相对应
public class OrderMaster {
    @Id
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

    private Date createTime;

    private Date updateTime;
}
