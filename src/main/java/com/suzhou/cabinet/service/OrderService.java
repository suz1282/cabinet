package com.suzhou.cabinet.service;


import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.suzhou.cabinet.entity.*;
import com.suzhou.cabinet.entity.bo.MainPageBO;
import com.suzhou.cabinet.entity.dto.MainPageDTO;
import com.suzhou.cabinet.entity.dto.OrderDTO;
import com.suzhou.cabinet.entity.vo.MainPageVO;
import com.suzhou.cabinet.enums.BoxSize;
import com.suzhou.cabinet.mapper.BoxMapper;
import com.suzhou.cabinet.mapper.OrderMapper;
import com.suzhou.cabinet.mapper.RegionMapper;
import com.suzhou.cabinet.utils.RestResult;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author suz
 * @since 2020-03-23
 */
@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    BoxMapper boxMapper;

    @Autowired
    RegionMapper regionMapper;

    public List<Order> listOrderByUserId(String id) {
        List<Order> l = orderMapper.selByCourierId(id);
        List<Order> orders = new ArrayList<>(32);
        l.forEach(order -> {
            Order o = new Order();
            o.setOrderCode(DateFormatUtils.format(order.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
            o.setBoxId(order.getBoxId());
            o.setId(order.getId());
            o.setCode(order.getCode());
            orders.add(o);
        });
        return ObjectUtils.isEmpty(orders) ? new ArrayList<>() : orders;
    }

    public synchronized RestResult<String> addManyOrder(OrderDTO orderDTO) {//测试用
        //快递柜合理存放算法
        double[] d = new double[]{orderDTO.getD(), orderDTO.getH(), orderDTO.getW()};
        Arrays.sort(d);
        Map<String, List<Box>> collect = boxMapper.selEmptyByCabinetId(orderDTO.getCabinetId()).stream().collect(Collectors.groupingBy(Box::getBoxType));
        //从小箱开始放
        Box box = null;
        box = fixBox(d, collect);
        if (!ObjectUtils.isEmpty(box)) {
            //boxMapper.updNotEmpty(box.getId());
            for (int x = 0; x < 7; x++) {
                double ran = Math.random() * 140.0;
                for (int j = 0; j < ran; j++) {
                    Order order = new Order();
                    Date date = new Date();// 获取当前时间

                    Calendar c = Calendar.getInstance();
                    c.setTime(date);

                    c.add(Calendar.DAY_OF_MONTH, -x);
                    Date date2 = c.getTime();

                    DateFormatUtils.format(date2, "yyyy-MM-dd HH:mm:ss");
                    order.setCreateTime(date2);
                    order.setBoxId(box.getId());
                    order.setCourierId(orderDTO.getCourierId());
                    order.setId(IdWorker.get32UUID());
                    StringBuilder code = new StringBuilder();
                    for (int i = 0; i < 8; i++) {
                        double random = Math.random() * 10.0;
                        code.append((int) random);
                    }
                    order.setCode(code.toString());
                    orderMapper.insOrder(order);
                }
            }

        }
        return RestResult.success("success");
    }

    public synchronized RestResult<String> addOrder(OrderDTO orderDTO) {
        //快递柜合理存放算法
        double[] d = new double[]{orderDTO.getD(), orderDTO.getH(), orderDTO.getW()};
        Arrays.sort(d);
        Map<String, List<Box>> collect = boxMapper.selEmptyByCabinetId(orderDTO.getCabinetId()).stream().collect(Collectors.groupingBy(Box::getBoxType));
        //从小箱开始放
        Box box = null;
        box = fixBox(d, collect);
        if (!ObjectUtils.isEmpty(box)) {
            boxMapper.updNotEmpty(box.getId());

            Order order = new Order();
            Date date = new Date();// 获取当前时间
            DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
            order.setCreateTime(date);
            order.setBoxId(box.getId());
            order.setCourierId(orderDTO.getCourierId());
            order.setId(IdWorker.get32UUID());
            StringBuilder code = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                double random = Math.random() * 10.0;
                code.append((int) random);
            }
            order.setCode(code.toString());
            orderMapper.insOrder(order);
        }
        return RestResult.success("success");
    }

    private Box fixBox(double[] d, Map<String, List<Box>> collect) {
        List<Box> boxes = collect.get("small");
        if (!ObjectUtils.isEmpty(boxes) || boxes.size() > 0) {//小箱有空
            double[] size = new double[]{BoxSize.SMALL_BOX_DEPTH.getMessage(), BoxSize.SMALL_BOX_HEIGHT.getMessage(), BoxSize.SMALL_BOX_DEPTH.getMessage()};
            Arrays.sort(size);
            if (d[0] <= size[0] && d[1] <= size[1] && d[2] <= size[2]) {
                return boxes.get(0);
            }
        }
        boxes = collect.get("middle");
        if (!ObjectUtils.isEmpty(boxes) || boxes.size() > 0) {//middle箱有空
            double[] size = new double[]{BoxSize.MIDDLE_BOX_DEPTH.getMessage(), BoxSize.MIDDLE_BOX_HEIGHT.getMessage(), BoxSize.MIDDLE_BOX_DEPTH.getMessage()};
            Arrays.sort(size);
            if (d[0] <= size[0] && d[1] <= size[1] && d[2] <= size[2]) {
                return boxes.get(0);
            }
        }
        boxes = collect.get("big");
        if (!ObjectUtils.isEmpty(boxes) || boxes.size() > 0) {//big箱有空
            double[] size = new double[]{BoxSize.BIG_BOX_DEPTH.getMessage(), BoxSize.BIG_BOX_HEIGHT.getMessage(), BoxSize.BIG_BOX_DEPTH.getMessage()};
            Arrays.sort(size);
            if (d[0] <= size[0] && d[1] <= size[1] && d[2] <= size[2]) {
                return boxes.get(0);
            }
        }
        return null;
    }

    public RestResult<List<MainPageVO>> mainPageTable(MainPageDTO mainPageDTO) {
        long startTime = Long.parseLong(mainPageDTO.getStartTime());
        long endTime = Long.parseLong(mainPageDTO.getEndTime());
        long stepTime = (endTime - startTime) / (mainPageDTO.getStep() * 1000 * 3600 * 24);
        List<MainPageBO> mainPageBOS = new ArrayList<>();
        List<MainPageVO> mainPageVOS = new ArrayList<>();
        List<Region> regions = regionMapper.selRegions();
        if (ObjectUtils.isEmpty(mainPageDTO.getRegionIds())) {
            List<String> l = new ArrayList<>();
            l.add(regions.get(0).getId());
            mainPageDTO.setRegionIds(l);
        }
        regions.forEach(r -> {
            MainPageVO m = new MainPageVO();
            if (mainPageDTO.getRegionIds().contains(r.getId())) {
                m.setId(r.getId());
                m.setName(r.getName());
                m.setData(new ArrayList<>());
                mainPageVOS.add(m);
            }
        });
        for (int i = 0; i < stepTime; i++) {
            List<MainPageBO> m = orderMapper.selMainPageTable(
                    DateFormatUtils.format(new Date(startTime), "yyyy-MM-dd HH:mm:ss"),
                    DateFormatUtils.format(new Date(startTime + mainPageDTO.getStep() * 1000 * 3600 * 24), "yyyy-MM-dd HH:mm:ss"));
            Map<String, List<MainPageBO>> collect = m.stream().collect(Collectors.groupingBy(MainPageBO::getId));
            for (int j = 0; j < mainPageVOS.size(); j++) {
                List<MainPageBO> mainPageBOS1 = collect.get(mainPageVOS.get(j).getId());
                MainPageVO mm = new MainPageVO();
                mm.setName(mainPageVOS.get(j).getName());
                mm.setId(mainPageVOS.get(j).getId());
                List<Integer> data = mainPageVOS.get(j).getData();
                if (ObjectUtils.isEmpty(mainPageBOS1)) {
                    data.add(0);
                } else {
                    data.add(mainPageBOS1.get(0).getData());
                }
                mm.setData(data);
                mainPageVOS.set(j, mm);
            }
            startTime += mainPageDTO.getStep() * 1000 * 3600 * 24;
        }

        return RestResult.success(mainPageVOS);
    }
}
