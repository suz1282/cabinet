package com.suzhou.cabinet.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suzhou.cabinet.entity.Region;
import com.suzhou.cabinet.entity.RegionTree;
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
public interface RegionMapper extends BaseMapper<Region>{

    List<Region> selAllRegions();

    Integer delRegionById(@Param("id") String id);

    List<Region> selRegionsByParentId(@Param("id") String id);

    Region selCombine(@Param("parentId") String parentId, @Param("seqNo") String seqNo);

    Integer selSeqNoCount(@Param("parentId") String parentId);

    List<RegionTree> selAllFatherPoint();

    List<RegionTree> selRegionsByFatherId(@Param("id") String id);

    Region selByNameAndParentId(@Param("name") String name, @Param("parentId") String parentId);

    Region selUpdateNameAndParentId(@Param("id") String id, @Param("name") String name, @Param("parentId") String parentId);

    List<RegionTree> selNotFather();

    List<Region> selRegions();

    void delRegionByIds(@Param("str") List<String> str);

    Region selectById(String parentId);
}
