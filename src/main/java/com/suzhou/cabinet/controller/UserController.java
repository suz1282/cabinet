package com.suzhou.cabinet.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suzhou.cabinet.entity.Order;
import com.suzhou.cabinet.entity.User;
import com.suzhou.cabinet.entity.dto.UserDTO;
import com.suzhou.cabinet.entity.vo.UserVO;
import com.suzhou.cabinet.service.UserService;
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
@RequestMapping("/user")
@Api(description = "用户管理")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public RestResult<User> login(@RequestBody User loginUser) {
        return userService.login(loginUser);
    }

    @GetMapping("/getUser2")
    @ApiOperation("获取所有快递员和其任务数")
    public RestResult<List<UserVO>> getUser2() {
        return userService.getUser2();
    }

    @GetMapping("/checkToken/{id}")
    @ApiOperation("检查Token")
    public RestResult<User> checkToken(@PathVariable("id") String id) {
        return userService.checkToken(id);
    }

    @GetMapping("/getUser2Mission/{id}")
    @ApiOperation("快递员id查其任务")
    public RestResult<List<Order>> getUser2Mission(@PathVariable("id") String id) {
        return userService.getUser2Missions(id);
    }

    @PostMapping("/getUsers/list")
    @ApiOperation("人员列表")
    public RestResult<Page<UserVO>> listUser(@RequestBody UserDTO dto) {
        return userService.listUser(dto);
    }

    @PostMapping("/addUser")
    @ApiOperation("添加用户")
    public RestResult<String> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/removeUser/{id}")
    @ApiOperation("删除用户")
    public RestResult<String> removeUser(@PathVariable("id") String id) {
        return userService.removeUser(id);
    }

}

