package com.suzhou.cabinet.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suzhou.cabinet.entity.Cabinet;
import com.suzhou.cabinet.entity.User;
import com.suzhou.cabinet.entity.UserVO;
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
public interface UserMapper extends BaseMapper<User> {

    User selUser(@Param("loginUser") User loginUser);

    List<UserVO> selUser2Num();
}
