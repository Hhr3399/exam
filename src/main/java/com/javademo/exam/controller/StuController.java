package com.javademo.exam.controller;


import com.javademo.exam.pojo.*;
import com.javademo.exam.service.StuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/stu")
public class StuController {
    @Autowired
    private StuService stuService;

    /**
     * TODO  感觉token中的id解析不出来
     *
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
     *
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
    @GetMapping("/{questionid}/{answername}")
    public Result getquestion(@PathVariable Integer questionid, @PathVariable String answername) {
        Stuexam stuexam = new Stuexam();
        Question question = stuService.getquestion(questionid);

        int totalscore = 0;
        if (question.getAnswer().equals(answername)) {
            totalscore += question.getSingle_score();
        }
        stuexam.setScore(totalscore);
        stuexam.setStime(LocalDateTime.now());
        return Result.success(stuexam);
    }

    /**
     * 进入考试，开始答题
     */
    public Result examing() {


        return Result.success();
    }

}
