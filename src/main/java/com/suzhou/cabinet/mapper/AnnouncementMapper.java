package com.suzhou.cabinet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suzhou.cabinet.entity.AnnounceSearchPage;
import com.suzhou.cabinet.entity.Announcement;
import com.suzhou.cabinet.entity.vo.AnnouncementVO;
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
@Repository
@Mapper
public interface AnnouncementMapper extends BaseMapper<Announcement> {
    Integer updAnnouncementById(@Param("id") String id);

    List<Announcement> selByCreatorId(@Param("id") String userId);

    int selCountAll();

    List<Announcement> selByPage(@Param("i") int i, @Param("size") int size);

    int selCountByTypeAndTitle(@Param("title") String title, @Param("type") String type);

    List<Announcement> selByTypeAndTitle(@Param("title") String title, @Param("type") String type, @Param("i") int i, @Param("size") int size);

    int selCountByTitle(@Param("title") String title);

    List<Announcement> selByTitle(@Param("title") String title, @Param("i") int i, @Param("size") int size);

    int selCountByType(@Param("type") String type);

    List<Announcement> selByType(@Param("type") String type, @Param("i") int i, @Param("size") int size);

    List<Announcement> selAnnouncePageList(Page<AnnouncementVO> page, @Param("asp") AnnounceSearchPage announceSearchPage);

    Announcement selAnnouncementById(@Param("announcementId") String announcementId);

}
