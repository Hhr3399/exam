package com.javademo.exam.service.impl;


import com.javademo.exam.mapper.StuMapper;
import com.javademo.exam.pojo.*;
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
    public List<Test> getCourse(Stuuser stuuser) {
        return stuMapper.courselist(stuuser);
    }

    @Override
    public Question getquestion(Integer questionid) {
        return stuMapper.getquestion(questionid);
    }

    @Override
    public Stuuser gets(Integer id) {
        return stuMapper.gets(id);
    }

    @Override
    public List<Question> selecttest(String name) {
        return stuMapper.selecttest(name);
    }

    @Override
    public void insertResult(Stuexam stuexam) {
        stuMapper.insertResult(stuexam);
    }

    @Override
    public List<Stuexam> selectStatus(Test t) {
        return stuMapper.selectStatus(t);
    }

    @Override
    public Stuexam selectLatest(Test t) {
        return stuMapper.selectLatest(t);
    }

    @Override
    public List<Course> getCoursename(Stuuser stuuser) {
        return stuMapper.gerCoursename(stuuser);
    }
}
