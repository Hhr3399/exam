package com.javademo.exam.service;

import com.javademo.exam.pojo.entity.Course;
import com.javademo.exam.pojo.entity.Question;
import com.javademo.exam.pojo.entity.StudentCourse;
import com.javademo.exam.pojo.entity.Stuuser;
import com.javademo.exam.pojo.vo.ExamResultVo;

import java.util.List;

public interface StuService {

    void update(Stuuser stuuser);

    Stuuser gets(int id);

    List<Question> getQuestions(String courseName);

    List<Course> getCoursename(Stuuser stuuser);

    List<ExamResultVo> getexamResults(int id);

    ExamResultVo getsigleResult(String courseName, int id);

    void saveResult(StudentCourse studentCourse);
}
