package com.javademo.exam.service.impl;


import com.javademo.exam.mapper.StuMapper;
import com.javademo.exam.pojo.Stuuser;
import com.javademo.exam.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuMapper stuMapper;

    @Override
    public List<Stuuser> list() {
        return stuMapper.list();
    }

    @Override
    public void update(Stuuser stuuser) {
        stuMapper.update(stuuser);
    }
}
