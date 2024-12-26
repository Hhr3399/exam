package com.javademo.exam.controller;


import com.javademo.exam.Utils.BcryptUtils;
import com.javademo.exam.Utils.JwtUtils;
import com.javademo.exam.common.JwtClaimsConstant;
import com.javademo.exam.pojo.entity.Result;
import com.javademo.exam.pojo.entity.Stuuser;
import com.javademo.exam.pojo.entity.Teauser;
import com.javademo.exam.properties.JwtProperties;
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

    @Autowired
    private JwtProperties jwtProperties;
    @PostMapping("/slogin")
    public Result login(@RequestBody Stuuser stuuser) {
        try {
            Stuuser stuuser1 = loginService.slogin(stuuser);
            if (stuuser1 == null) {
                return Result.error("用户不存在");
            }

            //boolean flag = BcryptUtils.verifyPassword(stuuser.getPassword(), stuuser1.getPassword());

            if (stuuser1!=null) {
                String userId = stuuser1.getSid();
                Map<String, Object> claims = new HashMap<>();
                claims.put(JwtClaimsConstant.USER_ID, userId);
                String token = JwtUtils.createJWT(
                        jwtProperties.getSecretKey(),
                        jwtProperties.getTtl(),
                        claims);
                // 返回成功结果
                return Result.success(token);
            } else {
                return Result.error("密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统错误: " + e.getMessage());
        }
    }



    @PostMapping("/tlogin")
    public  Result login(@RequestBody Teauser teauser){
        Teauser teauser1 =loginService.tlogin(teauser);
        if (teauser1 != null){
            String userId= teauser1.getTid();
            Map<String, Object> claims = new HashMap<>();
            claims.put(JwtClaimsConstant.USER_ID, userId);
            String token = JwtUtils.createJWT(
                    jwtProperties.getSecretKey(),
                    jwtProperties.getTtl(),
                    claims);
            return Result.success(token);//以后前端发送的每一次请求，在请求头中会携带token（存储着jwt令牌）
        }
        return Result.error("用户名或密码错误");
    }
}
