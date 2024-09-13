package com.javademo.exam.controller;


import com.javademo.exam.Utils.JwtUtils;
import com.javademo.exam.pojo.*;
import com.javademo.exam.service.StuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/stu")
public class StuController {
    @Autowired
    private StuService stuService;


    /**
     * 查找个人信息
     */
    @GetMapping("/getstudent")
    public Result gets(HttpServletRequest req) {
        String token = req.getHeader("token");
        Integer id = (Integer) JwtUtils.parseJWT(token).get("id");

        Stuuser stuuser = stuService.gets(id);
        return Result.success(stuuser);
    }


    /**
     * TODO  感觉token中的id解析不出来
     *
     * @param stuuser
     * @return
     */
    @PutMapping("/supdate")
    public Result update(@RequestBody Stuuser stuuser, HttpServletRequest req) {
        String token = req.getHeader("token");
        Integer id = (Integer) JwtUtils.parseJWT(token).get("id");
        System.out.println(stuuser);
        stuuser.setId(id);
        Stuuser stuuser2 = stuService.gets(id);

        System.out.println(stuuser2);
        if (stuuser.getStudent_name() == null) {
            stuuser.setStudent_name(stuuser2.getStudent_name());
        }
        if (stuuser.getGender() == 0) {
            stuuser.setGender(stuuser2.getGender());
        }
        if (stuuser.getPhonenumber() == null) {
            stuuser.setPhonenumber(stuuser2.getPhonenumber());
        }
        if (stuuser.getSid() == null) {
            stuuser.setSid(stuuser2.getSid());
        }
        if (stuuser.getCollegeId() == 0) {
            stuuser.setCollegeId(stuuser2.getCollegeId());
        }
        if (stuuser.getUsername() == null) {
            stuuser.setUsername(stuuser2.getUsername());
        }
        if (stuuser.getPassword() == null) {
            stuuser.setPassword(stuuser2.getPassword());
        }
        System.out.println(stuuser);

        stuService.update(stuuser);

        System.out.println(stuuser);

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
