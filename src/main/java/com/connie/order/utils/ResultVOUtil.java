package com.connie.order.utils;

import com.connie.order.VO.ResultVO;

/**
 * 普通类
 *
 * @Author:Jackie
 * @Date:Created in 2018-10-23 09:08:14
 */
public class ResultVOUtil {
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMes("成功");
        resultVO.setData(object);
        return resultVO;
    }
}
