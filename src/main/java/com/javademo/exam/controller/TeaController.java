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
@RequestMapping("/teas")
public class TeaController {

    @Autowired
    private TeaService teaService;

    /**
     * 显示该课程考题
     */
    @GetMapping
    public Result list() {

        List<Question> teausers = teaService.list();

        return Result.success(teausers);
    }

    /**
     * 教师增加考试题目
     *
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Question question) {
        teaService.add(question);

        return Result.success();
    }

    /**
     * 修改考试题目
     * @param question
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Question question){

        teaService.update(question);

        return Result.success();
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除老师:{}",id);
        teaService.delete(id);
        return Result.success();
    }

    /**
     * 展示所有考生的考试成绩的相关信息
     */
    @GetMapping("/ada")
    public  Result stuscore(){

        List<Stuexam> stuexams =teaService.stuscore();


        return Result.success("");
    }

}
