package com.connie.order.dto;

import lombok.Data;

/**
 * 普通类
 *
 * @Author:Jackie
 * @Date:Created in 2018-10-23 15:30:47
 */
@Data
public class CartDTO {

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer productQuantity;

    public CartDTO() {
    }

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
