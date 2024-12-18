package com.javademo.exam.controller;


import com.javademo.exam.Utils.BeanUtil;
import com.javademo.exam.Utils.JwtUtils;
import com.javademo.exam.pojo.entity.*;
import com.javademo.exam.pojo.vo.ExamResultVo;
import com.javademo.exam.pojo.vo.StudentVo;
import com.javademo.exam.properties.JwtProperties;
import com.javademo.exam.service.RegisterService;
import com.javademo.exam.service.StuService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/stu")
public class StuController {

    @Autowired
    private StuService stuService;

    @Autowired
    private RegisterService registerService;

    /**
     * 查找学生个人信息
     *
     * @param stuuser
     * @return
     */
    @GetMapping("/getstudent")
    public Result gets(@RequestBody Stuuser stuuser) {

        Stuuser stuuser1 = registerService.get(stuuser);
        StudentVo studentVo = new StudentVo();
        BeanUtils.copyProperties(stuuser1, studentVo);
        return Result.success(studentVo);
    }

    /**
     * 学生编辑个人信息
     *
     * @param stuuser
     * @param req
     * @return
     */
    @PutMapping("/supdate")
    public Result update(@RequestBody Stuuser stuuser) {

        Stuuser stuuser1 = stuService.gets(stuuser.getId());
        BeanUtil.copyNonNullProperties(stuuser, stuuser1);  //把stuuser的值赋值给stuuser2
        stuService.update(stuuser1);
        return Result.success("修改学生个人信息成功");

    }

    /**
     * 获取学生所选课程名字与id
     *
     * @param stuuser
     * @return
     */
    @PostMapping("/getcoursename")
    public Result getcoursename(@RequestBody Stuuser stuuser) {
        List<Course> courses = stuService.getCoursename(stuuser);
        return Result.success(courses);
    }

    /**
     * 显示学生所有课程的考试结果
     * @param id
     * @return
     */
    @PostMapping("/getexamResult/{id}")
    public Result getexamResults(@PathVariable int id) {
        List<ExamResultVo> examResultVo = stuService.getexamResults(id);
        return Result.success(examResultVo);
    }

    /**
     * 显示学生单个课程的考试结果
     * @param courseName
     * @param id
     * @return
     */
    @PostMapping("/getsingleResult/{courseName}/{id}")
    public Result getsingleResult(@PathVariable String courseName, @PathVariable int id) {

        ExamResultVo examResultVo = stuService.getsigleResult(courseName, id);
        return Result.success(examResultVo);

    }

    /**
     * 根据课程名字获取对应的考试题目并且开始考试
     * @param courseName
     * @param id
     * @return
     */
    @PostMapping("/getQuestions/{courseName}/{id}")
    public Result getQuestions(@PathVariable String courseName, @PathVariable int id) {

        List<Question> questions = stuService.getQuestions(courseName);

        int courseId = questions.get(0).getCourseId();
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setCId(courseId);
        studentCourse.setSId(id);
        studentCourse.setStime(LocalDateTime.now());
        Map<String, Object> claims = new HashMap<>();
        claims.put("questions", questions);
        claims.put("studentCourse", studentCourse);
        return Result.success(claims);
    }

    /**
     * 提交答案并校验，上传考试结果
     * @param claims
     * @param answers
     * @param id
     * @return
     */
    @RequestMapping("/submit/{id}")
    public Result submitAnswers(@RequestBody Map<String, Object> claims, @RequestBody List<String> answers,@PathVariable int id ) {

        List<Question> questions = (List<Question>) claims.get("questions");
        StudentCourse studentCourse = (StudentCourse) claims.get("studentCourse");

        int totalscore = 0;
        for (Question question : questions) {
            String correctanswer = question.getAnswer();
            for (String youranswer : answers) {
                if (youranswer.equals(correctanswer)) {
                    totalscore += question.getSingleScore();
                }
            }
        }
        studentCourse.setScore(totalscore);
        studentCourse.setEtime(LocalDateTime.now());

        stuService.saveResult(studentCourse);

        return Result.success("考试结束，结果已经上传");
    }


}
