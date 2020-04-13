package com.suzhou.cabinet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suzhou.cabinet.entity.Box;
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
public interface BoxMapper {

    void insInit(@Param("list") List<Box> boxes);
}
