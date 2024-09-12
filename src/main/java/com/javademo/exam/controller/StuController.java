package com.javademo.exam.controller;


import com.javademo.exam.pojo.Result;
import com.javademo.exam.pojo.Stuuser;
import com.javademo.exam.service.StuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/stus")
public class StuController {
    @Autowired
    private StuService stuService;


    /**
     * 查询所有学生信息
     */
    @GetMapping
    public Result list() {
        log.info("查询所有学生信息");
        List<Stuuser> stuList = stuService.list();
        return Result.success(stuList);
    }

    @PostMapping
    public Result update(@RequestBody Stuuser stuuser){

        stuService.update(stuuser);
        return Result.success();
    }


}
