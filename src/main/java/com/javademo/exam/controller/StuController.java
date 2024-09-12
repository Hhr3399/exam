package com.javademo.exam.controller;


import com.javademo.exam.pojo.Course;
import com.javademo.exam.pojo.Question;
import com.javademo.exam.pojo.Result;
import com.javademo.exam.pojo.Stuuser;
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


    @PostMapping("/supdate")
    public Result update(@RequestBody Stuuser stuuser) {

        stuService.update(stuuser);
        return Result.success();
    }

    @GetMapping("/getcourse")
    public Result getcourse(@RequestBody Stuuser stuuser) {

        List<Course> courses = stuService.getCourse(stuuser);
        return Result.success(courses);
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
