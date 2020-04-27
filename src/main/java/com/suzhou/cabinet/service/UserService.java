package com.suzhou.cabinet.service;


import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suzhou.cabinet.entity.*;
import com.suzhou.cabinet.entity.dto.UserDTO;
import com.suzhou.cabinet.entity.vo.UserVO;
import com.suzhou.cabinet.mapper.UserMapper;
import com.suzhou.cabinet.utils.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.suzhou.cabinet.utils.RestResult.fail;
import static com.suzhou.cabinet.utils.RestResult.success;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author suz
 * @since 2020-03-23
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrderService orderService;

    public RestResult<User> login(User loginUser) {
        User user = userMapper.selUser(loginUser);
        if(user!=null){
            return success(user);
        }
        return fail("login error","user do not exist");
    }

    public RestResult<List<UserVO>> getUser2() {
        return success(userMapper.selUser2Num());
    }

    public RestResult<User> checkToken(String id) {
        return success(userMapper.selectById(id));
    }

    public RestResult<List<Order>> getUser2Missions(String id) {
        List<Order> list=orderService.listOrderByUserId(id);
        return success(list);
    }

    public RestResult<Page<UserVO>> listUser(UserDTO dto) {
        Page<UserVO> page = new Page<>();//设定Page类
        page.setCurrent(dto.getCurrentPage() > 0 ? dto.getCurrentPage() : 1);
        page.setSize(Math.max(dto.getPageSize(), 5));//设置页面大小
        List<UserVO> userVOS = userMapper.selUserList(page, dto.getName());
        userVOS.forEach(userVO -> {
            String type=userVO.getType();
            if("2".equals(type)){
                userVO.setType("快递员");
            }else if("1".equals(type)){
                userVO.setType("超级管理员");
            }
        });
        page.setRecords(userVOS);
        return RestResult.success(page);
    }

    public RestResult<String> addUser(User user) {
        user.setId(IdWorker.get32UUID());
        user.setCreateTime(new Date());
        user.setDelFlag("0");
        userMapper.insert(user);
        return success("success");
    }

    public RestResult<String> removeUser(String id) {
        userMapper.updUserDelFlag(id);
        return success("success");
    }

    public RestResult<String> resetPassword(String id) {
        userMapper.updPassword(id,"123456");
        return success("success");
    }
}
