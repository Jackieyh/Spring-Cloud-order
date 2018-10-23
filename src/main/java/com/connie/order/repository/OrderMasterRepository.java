package com.connie.order.repository;

import com.connie.order.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口类
 *
 * @Author:Jackie
 * @Date:Created in 2018-10-22 16:38:16
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

}
