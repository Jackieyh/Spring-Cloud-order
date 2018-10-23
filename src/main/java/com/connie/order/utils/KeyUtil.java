package com.connie.order.utils;

import java.util.Random;
import java.util.UUID;

/**
 * 普通类
 *
 * @Author:Jackie
 * @Date:Created in 2018-10-22 17:14:21
 */
public class KeyUtil {

    public static synchronized String genUniqueKey() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
