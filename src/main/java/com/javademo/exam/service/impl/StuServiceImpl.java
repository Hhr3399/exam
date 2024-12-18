package com.javademo.exam.service.impl;


import com.javademo.exam.mapper.StuMapper;
import com.javademo.exam.pojo.*;
import com.javademo.exam.pojo.entity.Course;
import com.javademo.exam.pojo.vo.ExamResultVo;
import com.javademo.exam.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuMapper stuMapper;

    @Override
    public void update(Stuuser stuuser) {
        stuMapper.update(stuuser);
    }

    @Override
    public Stuuser gets(Integer id) {
        return stuMapper.gets(id);
    }

    @Override
    public List<Question> getQuestions(String courseName) {
        return stuMapper.getQuestions(courseName);
    }

    @Override
    public List<Course> getCoursename(Stuuser stuuser) {
        return stuMapper.gerCoursename(stuuser);
    }

    @Override
    public List<ExamResultVo> getexamResults(Integer id) {
       return stuMapper.getexamResults(id);
    }

    @Override
    public ExamResultVo getsigleResult(String courseName, Integer id) {
        return stuMapper.getsigleResult(courseName,id);
    }

    @Override
    public void saveResult(StudentCourse studentCourse) {
        stuMapper.saveResult(studentCourse);
    }
}
