package com.suzhou.cabinet.controller;


import com.suzhou.cabinet.entity.dto.MainPageDTO;
import com.suzhou.cabinet.entity.vo.MainPageVO;
import com.suzhou.cabinet.entity.dto.OrderDTO;
import com.suzhou.cabinet.service.OrderService;
import com.suzhou.cabinet.utils.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author suz
 * @since 2020-03-23
 */
@RestController
@RequestMapping("/order")
@Api(description = "订单管理")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/addOrder")
    @ApiOperation("添加订单")
    public RestResult<String> login(@RequestBody OrderDTO orderDTO) {
        return orderService.addOrder(orderDTO);
    }

    @PostMapping("/mainPageTable")
    @ApiOperation("主页表")
    public RestResult<List<MainPageVO>> login(@RequestBody MainPageDTO mainPageDTO) {
        return orderService.mainPageTable(mainPageDTO);
    }
}

