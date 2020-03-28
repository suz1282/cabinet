package com.suzhou.cabinet.controller;


import com.suzhou.cabinet.entity.User;
import com.suzhou.cabinet.service.UserService;
import com.suzhou.cabinet.utils.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Connection;
import java.sql.Driver;

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

    @GetMapping("/getUser")
    @ApiOperation("获取用户")
    public User getUser() {
        User u =new User();
        u.setId("123");
        u.setName("suzhou");
        return u;
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public RestResult<String> login(@RequestBody User loginUser) {
        return userService.login(loginUser);
    }
    @PostMapping("/register")
    @ApiOperation("用户注册")
    public RestResult<String> register(@RequestBody User loginUser) {
        return userService.login(loginUser);
    }

}

