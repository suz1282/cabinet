package com.suzhou.cabinet.service;


import com.suzhou.cabinet.entity.Order;
import com.suzhou.cabinet.entity.User;
import com.suzhou.cabinet.entity.UserVO;
import com.suzhou.cabinet.mapper.OrderMapper;
import com.suzhou.cabinet.mapper.UserMapper;
import com.suzhou.cabinet.utils.RestResult;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
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
}
