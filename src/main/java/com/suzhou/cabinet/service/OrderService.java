package com.suzhou.cabinet.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suzhou.cabinet.entity.Order;
import com.suzhou.cabinet.entity.OrderDTO;
import com.suzhou.cabinet.mapper.OrderMapper;
import com.suzhou.cabinet.utils.RestResult;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author suz
 * @since 2020-03-23
 */
@Service
public class OrderService{

    public RestResult<String> addOrder(OrderDTO orderDTO) {
        return null;
    }
}
