package com.javademo.exam.controller;


import com.javademo.exam.Utils.BeanUtil;
import com.javademo.exam.Utils.GetIdUtil;
import com.javademo.exam.Utils.JwtUtils;
import com.javademo.exam.pojo.entity.Question;
import com.javademo.exam.pojo.entity.Result;
import com.javademo.exam.pojo.entity.Teauser;
import com.javademo.exam.pojo.vo.MyStudentResultVo;
import com.javademo.exam.pojo.vo.TeacherVo;
import com.javademo.exam.properties.JwtProperties;
import com.javademo.exam.service.RegisterService;
import com.javademo.exam.service.TeaService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tea")
public class TeaController {


    @Autowired
    private RegisterService registerService;

    @Autowired
    private TeaService teaService;

    /**
     * 教师查询个人信息
     * @param teauser
     * @return
     */
    @GetMapping("/gettea")
    public Result gettea(@RequestBody Teauser teauser) {

        Teauser teauser1 = registerService.tget(teauser);
        TeacherVo teacherVo=new TeacherVo();
        BeanUtils.copyProperties(teauser1, teacherVo);
        return Result.success(teacherVo);

    }

    /**
     * 教师编辑个人信息
     * @param teauser
     * @param req
     * @return
     */
    @PutMapping("/tupdate")
    public Result tupdate(@RequestBody Teauser teauser) {

        int id=teauser.getId();
        Teauser teauser1 = teaService.gettea(id);
        BeanUtil.copyNonNullProperties(teauser, teauser1);  //把tuser的值赋值给teauser1

        teaService.tupdate(teauser1);
        return Result.success("修改教师信息成功");
    }


    /**
     * 显示所教课程考题
     * @param courseName
     * @return
     */
    @GetMapping("/getMyQuestions/{courseName}")
    public Result getMyQuestions(@PathVariable String courseName) {

        List<Question> questions = teaService.list(courseName);

        return Result.success(questions);
    }

    /**
     * 教师增加题目
     * @param question
     * @return
     */
    @PostMapping("/addquestion")
    public Result add(@RequestBody Question question) {

        teaService.add(question);
        return Result.success();
    }

    /**
     * @param id 根据id删除题目
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        teaService.delete(id);
        return Result.success();
    }

    /**
     * 教师修改题目
     * @param question
     * @return
     */
    @PutMapping("/updatequestion")
    public Result update(@RequestBody Question question) {
        teaService.update(question);
        return Result.success();
    }

    /**
     * 教师查看自己学生的成绩
     * @param courseid
     * @return
     */
    @GetMapping("/getStuResult/{courseid}")
    public Result getStuResult(@PathVariable Integer courseid) {
        List<MyStudentResultVo> myStudentResults = teaService.getStuResult(courseid);
        return Result.success("ok");
    }

    /**
     * 教师查看自己学生历次考试的最高成绩
     * @param courseid
     * @return
     */
    @GetMapping("/getStuMaxResult/{courseid}")
    public Result getStuMaxResult(@PathVariable Integer courseid) {
        List<MyStudentResultVo> myStudentResults = teaService.getStuMaxResult(courseid);
        return Result.success("ok");
    }
}
