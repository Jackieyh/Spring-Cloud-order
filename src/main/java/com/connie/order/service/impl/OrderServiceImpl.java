package com.connie.order.service.impl;

import com.connie.order.client.ProductClient;
import com.connie.order.dataobject.OrderDetail;
import com.connie.order.dataobject.OrderMaster;
import com.connie.order.dataobject.ProductInfo;
import com.connie.order.dto.CartDTO;
import com.connie.order.dto.OrderDTO;
import com.connie.order.enums.OrderStatusEnum;
import com.connie.order.enums.PayStatusEnum;
import com.connie.order.enums.ResultEnum;
import com.connie.order.exception.OrderException;
import com.connie.order.repository.OrderDetailRepository;
import com.connie.order.repository.OrderMasterRepository;
import com.connie.order.service.OrderService;
import com.connie.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 普通类
 *
 * @Author:Jackie
 * @Date:Created in 2018-10-22 17:05:44
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        // 2.查询商品信息(调用商品服务)
        List<String> productIdList = orderDTO.getOrderDetailList()
                .stream().map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfo> productInfoList = productClient.listForOrder(productIdList);

        // 读redis
        // 减库存并将新值重新设置进redis

        // 订单入库异常，手动回滚redis

        // 3.计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ONE);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (ProductInfo productInfo : productInfoList) {
                if (orderDetail.getProductId().equals(productInfo.getProductId())) {
                    // 单价*数量
                    orderAmount = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmount);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    // 订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

        // 4.扣库存(调用商品服务)
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList()
                .stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(cartDTOList);

        // 5.订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(String orderId) {
        // 1.先查询订单
        Optional<OrderMaster> orderMasterOptional = orderMasterRepository.findById(orderId);
        if (!orderMasterOptional.isPresent()) {
            throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }

        // 2.判断订单状态
        OrderMaster orderMaster = orderMasterOptional.get();
        if (!OrderStatusEnum.NEW.getCode().equals(orderMaster.getOrderStatus())) {
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // 3.修改订单状态为完结
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderMasterRepository.save(orderMaster);

        // 查询订单详情
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new OrderException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
