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
        String jwt = req.getHeader("Authorization");
        String token=null;
        if (jwt != null && jwt.startsWith("Bearer ")) {
            token = jwt.substring(7); // 去除"Bearer "前缀
            // 现在你可以使用jwt变量中的令牌进行后续处理
        }
        Integer id = (Integer) JwtUtils.parseJWT(token).get("id");

        Stuuser stuuser = stuService.gets(id);
        return Result.success(stuuser);
    }

    /**
     * 获取学生课程名字
     * @param stuuser
     * @return
     */

    @PostMapping("/getcoursename")
    public  Result getcoursename(@RequestBody Stuuser stuuser){

        System.out.println(stuuser);

        List<Test> tests = stuService.getCourse(stuuser);
        List<Course> courses= stuService.getCoursename(stuuser);

        return  Result.success(courses);
    }

    /**
     * @param stuuser
     * @return
     */
    @PutMapping("/supdate")
    public Result update(@RequestBody Stuuser stuuser, HttpServletRequest req) {
        String jwt = req.getHeader("Authorization");
        String token=null;
        if (jwt != null && jwt.startsWith("Bearer ")) {
            token = jwt.substring(7); // 去除"Bearer "前缀
            // 现在可以使用jwt变量中的令牌进行后续处理
        }
        Integer id = (Integer) JwtUtils.parseJWT(token).get("id");
        System.out.println(stuuser);
        stuuser.setId(id);
        Stuuser stuuser2 = stuService.gets(id);

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
        if ( stuuser.getCollegeId() == null) {
            stuuser.setCollegeId((Integer) stuuser2.getCollegeId());
        }
        if (stuuser.getUsername() == null) {
            stuuser.setUsername(stuuser2.getUsername());
        }
        if (stuuser.getPassword() == null) {
            stuuser.setPassword(stuuser2.getPassword());
        }
        System.out.println(stuuser);

        stuService.update(stuuser);

        return Result.success();

    }

    /**
     * 显示学生所选课程
     *
     * @param stuuser
     * @return
     */
    @PostMapping("/getcourse")
    public Result getcourse(@RequestBody Stuuser stuuser) {
        System.out.println(stuuser);
        //获得所选课程列表
        List<Test> tests = stuService.getCourse(stuuser);
        System.out.println(tests);
        for(Test t:tests){
            List<Stuexam> stuexams=stuService.selectStatus(t);
            System.out.println(stuexams);
            if(stuexams.size()==0){
                t.setStatus("未考试");
            }
            else{
                t.setStatus("已考试");
                Stuexam stuexam=stuService.selectLatest(t);
                t.setScore(stuexam.getScore());
            }
        }
        return Result.success(tests);
    }

    /**
     * 进入考试，获取到所有题目
     */

    @PostMapping("/exam")
    public Result examing(@RequestBody Course course) {
        List<Question> questions=stuService.selecttest(course.getName());
        return Result.success(questions);
    }
    /**
     * 根据题目id获得题目
     */
    @GetMapping("/{questionid}/{answername}")
    public Result getquestion(@PathVariable Integer questionid, @PathVariable Boolean answername) {//@PathVariable String answername
        Stuexam stuexam = new Stuexam();
        Question question = stuService.getquestion(questionid);

        int totalscore = 0;
        if (question.isAnswer()==answername) {//question.isAnswer()==(answername)
            totalscore += question.getSingleScore();
        }
        stuexam.setScore(totalscore);
        stuexam.setStime(LocalDateTime.now());
        return Result.success(stuexam);
    }

    /**
     * 储存考试结果
     * @param stuexam
     * @return
     */
    @PostMapping("/saveResult")
    public Result examresult(@RequestBody Stuexam stuexam){
        stuService.insertResult(stuexam);
        return Result.success("ok");
    }

}
