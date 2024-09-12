package com.javademo.exam.service.impl;

import com.javademo.exam.mapper.RegisterMapper;
import com.javademo.exam.mapper.TeaMapper;
import com.javademo.exam.pojo.Stuuser;
import com.javademo.exam.pojo.Teauser;
import com.javademo.exam.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {
   @Autowired
   private RegisterMapper registerMapper;
    @Override
    public Stuuser get(Stuuser stuuser) {
        return registerMapper.get(stuuser);
    }

    @Override
    public void add(Stuuser stuuser) {
        registerMapper.add(stuuser);
    }

    @Override
    public Teauser tget(Teauser teauser) {
        return registerMapper.tget(teauser);
    }

    @Override
    public void tadd(Teauser teauser) {
        registerMapper.tadd(teauser);
    }
}
