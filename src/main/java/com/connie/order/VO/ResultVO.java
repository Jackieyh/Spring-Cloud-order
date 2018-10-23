package com.connie.order.VO;

import lombok.Data;

/**
 * 普通类
 *
 * @Author:Jackie
 * @Date:Created in 2018-10-23 09:06:46
 */
@Data
public class ResultVO<T> {
    private Integer code;

    private String mes;

    private T data;
}
