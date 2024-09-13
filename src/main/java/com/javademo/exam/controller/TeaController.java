package com.javademo.exam.controller;


import com.javademo.exam.Utils.JwtUtils;
import com.javademo.exam.pojo.*;
import com.javademo.exam.service.TeaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tea")
public class TeaController {

    @Autowired
    private TeaService teaService;

    /**
     * 查询教师个人信息
     */

    @GetMapping("/gettea")
    public Result gettea(HttpServletRequest req) {
        String token = req.getHeader("token");
        Integer id = (Integer) JwtUtils.parseJWT(token).get("id");

        Teauser teauser = teaService.gettea(id);
        return Result.success(teauser);
    }

    /**
     * 教师编辑个人信息  TODO 感觉token中的id解析不出来
     */

    @PutMapping("/tupdate")
    public Result tupdate(@RequestBody Teauser teauser, HttpServletRequest req) {
        String token = req.getHeader("token");
        Integer id = (Integer) JwtUtils.parseJWT(token).get("id");
        System.out.println(teauser);
        teauser.setId(id);


        Teauser teauser2 = teaService.gettea(id);
        System.out.println(teauser2);

        if (teauser.getName() == null) {
            teauser.setName(teauser2.getName());
        }
        if (teauser.getGender() == null) {
            teauser.setGender((Integer) teauser2.getGender());
        }
        if (teauser.getPhonenumber() == null) {
            teauser.setPhonenumber(teauser2.getPhonenumber());
        }
        if (teauser.getTid() == null) {
            teauser.setTid(teauser2.getTid());
        }
        if (teauser.getCollegeId() == null) {
            teauser.setCollegeId((Integer) teauser2.getCollegeId());
        }
        if (teauser.getUsername() == null) {
            teauser.setUsername(teauser2.getUsername());
        }
        if (teauser.getPassword() == null) {
            teauser.setPassword(teauser2.getPassword());
        }
        if (teauser.getCourseId()==null){
            teauser.setCourseId(teauser2.getCourseId());
        }
        System.out.println(teauser);
        teaService.tupdate(teauser);
        return Result.success();
    }


    /**
     * 显示该课程考题
     */
    @GetMapping("/courseId/{courseId}")
    public Result list(@PathVariable Integer courseId) {

        List<Question> questions = teaService.list(courseId);

        return Result.success(questions);
    }

    /**
     * 教师增加考试题目
     *
     * @return
     */
    @PostMapping("/addquestion")
    public Result add(@RequestBody Question question) {
        teaService.add(question);

        return Result.success();
    }

    /**
     * 修改考试题目
     *
     * @param question
     * @return
     */
    @PutMapping("/updatequestion")
    public Result update(@RequestBody Question question) {

        teaService.update(question);

        return Result.success();
    }

    /**
     * @param id 根据id删除题目
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("根据id删除题目:{}", id);
        teaService.delete(id);
        return Result.success();
    }

    /**
     * 展示所有考生的考试成绩的相关信息
     */
    @GetMapping("/getscore")
    public Result stuscore() {

        List<Stuexam> stuexams = teaService.stuscore();
        return Result.success(stuexams);
    }

    /**
     * 展示某考生具体的考试情况
     */

    @GetMapping("/studentid/{studentid}")
    public Result getsexam(@PathVariable Integer studentid) {

        List<Stuexam> stuexams = teaService.getsexam(studentid);
        return Result.success(stuexams);
    }


}
