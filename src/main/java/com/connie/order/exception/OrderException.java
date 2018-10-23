package com.connie.order.exception;

import com.connie.order.enums.ResultEnum;

/**
 * 普通类
 *
 * @Author:Jackie
 * @Date:Created in 2018-10-23 08:25:37
 */
public class OrderException extends RuntimeException {
    private Integer code;

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
