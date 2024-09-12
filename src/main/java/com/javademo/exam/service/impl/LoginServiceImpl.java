package com.javademo.exam.service.impl;

import com.javademo.exam.mapper.LoginMapper;
import com.javademo.exam.pojo.Stuuser;
import com.javademo.exam.pojo.Teauser;
import com.javademo.exam.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public Stuuser login(Stuuser stuuser) {


        return loginMapper.getBynp(stuuser);
    }

    @Override
    public Teauser login(Teauser teauser) {
        return loginMapper.getBynp(teauser);
    }
}
