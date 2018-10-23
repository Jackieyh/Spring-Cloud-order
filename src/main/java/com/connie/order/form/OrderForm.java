package com.connie.order.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 普通类
 *
 * @Author:Jackie
 * @Date:Created in 2018-10-23 08:18:16
 */
@Data
public class OrderForm {

    @NotEmpty(message = "姓名必填")
    private String name;

    @NotEmpty(message = "电话必填")
    private String phone;

    @NotEmpty(message = "地址必填")
    private String address;

    @NotEmpty(message = "微信号必填")
    private String openid;

    @NotEmpty(message = "购物车不能为空")
    private String items;
}
