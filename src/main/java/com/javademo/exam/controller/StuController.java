package com.javademo.exam.controller;


import com.javademo.exam.pojo.*;
import com.javademo.exam.service.StuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/stu")
public class StuController {
    @Autowired
    private StuService stuService;

    /**
     * TODO  感觉token中的id解析不出来
     * @param stuuser
     * @return
     */
    @PutMapping("/supdate")
    public Result update(@RequestBody Stuuser stuuser) {

        stuService.update(stuuser);
        return Result.success();
    }

    /**
     * 显示学生所选课程
     * @param stuuser
     * @return
     */
    @GetMapping("/getcourse")
    public Result getcourse(@RequestBody Stuuser stuuser) {

        List<Test> tests = stuService.getCourse(stuuser);
        return Result.success(tests);
    }


    /**
     * 根据题目id获得题目
     */
    @GetMapping("/{questionid}")
    public Result getquestion(@PathVariable Integer questionid) {
       Question question= stuService.getquestion(questionid);

        return Result.success();
    }

}
