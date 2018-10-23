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
 * @Date:Created in 2018-10-22 11:47:16
 */
@Table(name = "product_info")
@Data   //lombok注解,省去get/set
@Entity //类与数据库表相对应
public class ProductInfo {

    // 主键
    @Id
    private String productId;

    // 名字
    private String productName;

    // 单价
    private BigDecimal productPrice;

    // 库存
    private Integer productStock;

    // 描述
    private String productDescription;

    // 小图
    private String productIcon;

    // 状态(0:正常  1:下架)
    private Integer productStatus;

    // 类目编号
    private Integer categoryType;

    private Date createTime;
    private Date updateTime;
}
