package com.javademo.exam.service;

import com.javademo.exam.pojo.entity.Question;
import com.javademo.exam.pojo.entity.Teauser;
import com.javademo.exam.pojo.vo.MyStudentResultVo;

import java.util.List;

public interface TeaService {

    Teauser gettea(Integer id);

    List<Question> list(String courseName);

    void add( Question question);

    void delete(Integer id);

    void update(Question question);

    void tupdate(Teauser teauser);


    List<MyStudentResultVo> getStuResult(Integer courseid);

    List<MyStudentResultVo> getStuMaxResult(Integer courseid);
}
