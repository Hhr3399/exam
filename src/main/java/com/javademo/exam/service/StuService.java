package com.javademo.exam.service;

import com.javademo.exam.pojo.*;
import com.javademo.exam.pojo.entity.Course;
import com.javademo.exam.pojo.vo.ExamResultVo;

import java.util.List;

public interface StuService {

    void update(Stuuser stuuser);

    Stuuser gets(Integer id);

    List<Question> getQuestions(String courseName);

     List<Course> getCoursename(Stuuser stuuser);

    List<ExamResultVo> getexamResults(Integer id);

    ExamResultVo getsigleResult(String courseName, Integer id);

    void saveResult(StudentCourse studentCourse);
}
