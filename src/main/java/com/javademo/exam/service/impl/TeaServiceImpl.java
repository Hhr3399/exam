package com.javademo.exam.service.impl;


import com.javademo.exam.mapper.TeaMapper;
import com.javademo.exam.pojo.Question;
import com.javademo.exam.pojo.Stuexam;
import com.javademo.exam.pojo.Teauser;
import com.javademo.exam.service.TeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeaServiceImpl implements TeaService {

    @Autowired
    private TeaMapper teaMapper;

    @Override
    public List<Question> list(Integer courseId) {
        return teaMapper.list(courseId);
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
    public List<Stuexam> stuscore() {
        return teaMapper.listscore();
    }

    @Override
    public void tupdate(Teauser teauser) {
        teaMapper.tupdate(teauser);
    }

    @Override
    public List<Stuexam> getsexam(Integer id) {
        return teaMapper.getsexam(id);
    }
}
