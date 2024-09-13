package com.javademo.exam.service;

import com.javademo.exam.pojo.*;

import java.util.List;

public interface StuService {

    void update(Stuuser stuuser);

    List<Test> getCourse(Stuuser stuuser);

    Question getquestion(Integer questionid);

    Stuuser gets(Integer id);

    List<Question> selecttest(String name);

    void insertResult(Stuexam stuexam);

    List<Stuexam> selectStatus(Test t);

    Stuexam selectLatest(Test t);
}
