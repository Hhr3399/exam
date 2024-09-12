package com.javademo.exam.controller;


import com.javademo.exam.pojo.Question;
import com.javademo.exam.pojo.Result;
import com.javademo.exam.pojo.Stuexam;
import com.javademo.exam.pojo.Teauser;
import com.javademo.exam.service.TeaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tea")
public class TeaController {

    @Autowired
    private TeaService teaService;

    /**
     * 教师编辑个人信息
     */

    @PutMapping("/tupdate")
    public Result tupdate(@RequestBody Teauser teauser) {

        teaService.tupdate(teauser);

        return Result.success();
    }


    /**
     * 显示该课程考题
     */
    @GetMapping("/{courseId}")
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

    @GetMapping("/{studentid}")
    public Result getsexam(@PathVariable Integer studentid) {

        List<Stuexam> stuexams= teaService.getsexam(studentid);
        return Result.success(stuexams);
    }

    /**
     * 进入考试，开始答题
     */

    public  Result examing(){


        return Result.success();
    }

}
