package com.javademo.exam.controller;


import com.javademo.exam.pojo.Result;
import com.javademo.exam.pojo.Stuuser;
import com.javademo.exam.pojo.Teauser;
import com.javademo.exam.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RegisterController {
    @Autowired
    private RegisterService registerService;


    @PostMapping("/sregister")
    public Result register(@RequestBody Stuuser stuuser) {

        Stuuser stuuser1 = registerService.get(stuuser);

        if (stuuser1 != null) {
            return Result.error("该用户已存在");
        } else {
            registerService.add(stuuser);
            return Result.success("注册成功");
        }
    }
    @PostMapping("/tregister")
    public Result register(@RequestBody Teauser teauser){
        Teauser teauser1 = registerService.tget(teauser);

        if (teauser1 != null) {
            return Result.error("该用户已存在");
        } else {
            registerService.tadd(teauser);
            return Result.success("注册成功");
        }
    }


}
