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
    public Teauser get(Teauser teauser) {
        return registerMapper.get(teauser);
    }

    @Override
    public void add(Teauser teauser) {
        registerMapper.add(teauser);
    }
}
