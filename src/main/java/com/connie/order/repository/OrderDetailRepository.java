package com.connie.order.repository;

import com.connie.order.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 接口类
 *
 * @Author:Jackie
 * @Date:Created in 2018-10-22 16:39:42
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    List<OrderDetail> findByOrderId(String orderId);

}
