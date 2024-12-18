package com.javademo.exam.controller;


import com.javademo.exam.Utils.BeanUtil;
import com.javademo.exam.Utils.GetIdUtil;
import com.javademo.exam.Utils.JwtUtils;
import com.javademo.exam.pojo.*;
import com.javademo.exam.pojo.entity.Course;
import com.javademo.exam.pojo.vo.ExamResultVo;
import com.javademo.exam.pojo.vo.StudentVo;
import com.javademo.exam.properties.JwtProperties;
import com.javademo.exam.service.StuService;
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
    private JwtProperties jwtProperties;

    /**
     * 查找学生个人信息
     */
    @GetMapping("/getstudent")
    public Result gets(HttpServletRequest req) {
        String token = req.getHeader("token");
        Integer id = (Integer) JwtUtils.parseJWT(jwtProperties.getSecretKey(), token).get("id");
        Stuuser stuuser = stuService.gets(id);
        StudentVo studentVo = new StudentVo();
        BeanUtils.copyProperties(stuuser, studentVo);
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
    public Result update(@RequestBody Stuuser stuuser, HttpServletRequest req) {

        String token = req.getHeader("token");
        Integer id = (Integer) JwtUtils.parseJWT(jwtProperties.getSecretKey(), token).get("id");
        stuuser.setId(id);
        Stuuser stuuser1 = stuService.gets(id);
        BeanUtil.copyNonNullProperties(stuuser, stuuser1);  //把stuuser的值赋值给stuuser2
        /*
            if (stuuser.getStudentName() == null) {
                stuuser.setStudentName(stuuser2.getStudentName());
            }
            if (stuuser.getGender() == null) {
                stuuser.setGender((Integer) stuuser2.getGender());
            }
            if (stuuser.getPhonenumber() == null) {
                stuuser.setPhonenumber(stuuser2.getPhonenumber());
            }
            if (stuuser.getSid() == null) {
                stuuser.setSid(stuuser2.getSid());
            }
            if (stuuser.getCollegeId() == null) {
                stuuser.setCollegeId((Integer) stuuser2.getCollegeId());
            }
            if (stuuser.getUsername() == null) {
                stuuser.setUsername(stuuser2.getUsername());
            }
            if (stuuser.getPassword() == null) {
                stuuser.setPassword(stuuser2.getPassword());
            }
        }*/
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
     *
     * @param req
     * @return
     */
    @PostMapping("/getexamResult")
    public Result getexamResults(HttpServletRequest req) {
        String token = req.getHeader("token");
        Integer id = (Integer) JwtUtils.parseJWT(jwtProperties.getSecretKey(), token).get("id");
        List<ExamResultVo> examResultVo = stuService.getexamResults(id);
        return Result.success(examResultVo);
    }

    /**
     * 显示学生单个课程的考试结果
     *
     * @param courseName
     * @param req
     * @return
     */
    @PostMapping("/getsingleResult")
    public Result getsingleResult(@RequestParam String courseName, HttpServletRequest req) {
        Integer id = GetIdUtil.getId(req);
        ExamResultVo examResultVo = stuService.getsigleResult(courseName, id);
        return Result.success(examResultVo);
    }

    /**
     * 根据课程名字获取对应的考试题目并且开始考试
     *
     * @param courseName
     * @param req
     * @return
     */
    @PostMapping("/getQuestions")
    public Result getQuestions(@RequestParam String courseName, HttpServletRequest req) {

        List<Question> questions = stuService.getQuestions(courseName);

        Integer sid = GetIdUtil.getId(req);
        Integer courseId = questions.get(0).getCourseId();
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setCId(courseId);
        studentCourse.setSId(sid);
        studentCourse.setStime(LocalDateTime.now());
        Map<String, Object> claims = new HashMap<>();
        claims.put("questions", questions);
        claims.put("studentCourse", studentCourse);
        return Result.success(claims);
    }

    /**
     * 提交答案并校验，上传考试结果
     *
     * @param claims
     * @param answers
     * @param req
     * @return
     */
    @RequestMapping("/submit")
    public Result submitAnswers(@RequestBody Map claims, @RequestBody List<String> answers, HttpServletRequest req) {

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
