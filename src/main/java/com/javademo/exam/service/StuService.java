package com.javademo.exam.service;

import com.javademo.exam.pojo.Course;
import com.javademo.exam.pojo.Question;
import com.javademo.exam.pojo.Stuuser;
import com.javademo.exam.pojo.Test;

import java.util.List;

public interface StuService {

    void update(Stuuser stuuser);

    List<Test> getCourse(Stuuser stuuser);

    Question getquestion(Integer questionid);

    Stuuser gets(Integer id);
}
