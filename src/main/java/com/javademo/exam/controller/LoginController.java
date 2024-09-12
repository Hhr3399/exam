package com.javademo.exam.controller;


import com.javademo.exam.Utils.JwtUtils;
import com.javademo.exam.pojo.Result;
import com.javademo.exam.pojo.Stuuser;
import com.javademo.exam.pojo.Teauser;
import com.javademo.exam.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/slogin")
    public Result login(@RequestBody Stuuser stuuser) {
        Stuuser stuuser1=loginService.login(stuuser);

        /**
         * 登陆成功，生成令牌，下发令牌
         */

        if (stuuser1 != null){
            Map<String, Object> claims =new HashMap<>();
            claims.put("id",stuuser1.getId());
            claims.put("username",stuuser1.getUsername());
            claims.put("password",stuuser1.getPassword());

            String jwt = JwtUtils.generateJwt(claims);   //jwt包含了当前学生的信息

            return Result.success(jwt);//以后前端发送的每一次请求，在请求头中会携带token（存储着jwt令牌）
        }

        return Result.error("用户名或密码错误");
    }


    @PostMapping("/tlogin")
    public  Result login(@RequestBody Teauser teauser){
        Teauser teauser1 =loginService.login(teauser);
        if (teauser1 != null){
            Map<String, Object> claims =new HashMap<>();
            claims.put("id",teauser1.getId());
            claims.put("username",teauser1.getUsername());
            claims.put("password",teauser1.getPassword());

            String jwt = JwtUtils.generateJwt(claims);   //jwt包含了当前学生的信息

            return Result.success(jwt);//以后前端发送的每一次请求，在请求头中会携带token（存储着jwt令牌）
        }

        return Result.error("用户名或密码错误");
    }
}
