package com.javademo.exam.service.impl;


import com.javademo.exam.mapper.TeaMapper;
import com.javademo.exam.pojo.entity.Question;
import com.javademo.exam.pojo.entity.Teauser;
import com.javademo.exam.pojo.vo.MyStudentResultVo;
import com.javademo.exam.service.TeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeaServiceImpl implements TeaService {

    @Autowired
    private TeaMapper teaMapper;

    @Override
    public Teauser gettea(Integer id) {
        return teaMapper.gettea(id);
    }

    @Override
    public List<Question> list(String courseName) {
        return teaMapper.list(courseName);
    }

    @Override
    public void add(Question question) {
        teaMapper.add(question);
    }

    @Override
    public void update(Question question) {
        teaMapper.update(question);
    }

    @Override
    public void delete(Integer id) {
        teaMapper.delete(id);
    }

    @Override
    public void tupdate(Teauser teauser) {
        teaMapper.tupdate(teauser);
    }

    @Override
    public List<MyStudentResultVo> getStuResult(Integer courseid) {
        return teaMapper.getStuResult(courseid);
    }

    @Override
    public List<MyStudentResultVo> getStuMaxResult(Integer courseid) {
        return teaMapper.getStuMaxResult(courseid);
    }


}
