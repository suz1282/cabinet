package com.suzhou.cabinet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suzhou.cabinet.entity.bo.MainPageBO;
import com.suzhou.cabinet.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author suz
 * @since 2020-03-23
 */
@Mapper
@Repository
public interface OrderMapper extends BaseMapper<Order> {

    void insOrder(@Param("order") Order order);

    List<Order> selByCourierId(String id);

    List<MainPageBO> selMainPageTable(@Param("start") String startTime,@Param("end") String endTime);

    Integer updByCabinetAndOrderCode(@Param("cabinetId") String cabinetId,@Param("code") String orderCode);

    void updOrderIn(@Param("code") String orderCode,@Param("cid") String cabinetId);
}
