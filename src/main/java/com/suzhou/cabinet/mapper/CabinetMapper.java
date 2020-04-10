package com.suzhou.cabinet.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suzhou.cabinet.entity.Cabinet;
import com.suzhou.cabinet.entity.CabinetVO;
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
public interface CabinetMapper extends BaseMapper<Cabinet> {

    void updCabinet(String id);

    List<Cabinet> selByRegionId(String id);

    List<Cabinet> selAllCabinet();

    List<Cabinet> selCabinetByPage(Page<CabinetVO> page);
}
