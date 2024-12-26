package com.javademo.exam.service.impl;

import com.javademo.exam.mapper.LoginMapper;
import com.javademo.exam.pojo.entity.Stuuser;
import com.javademo.exam.pojo.entity.Teauser;
import com.javademo.exam.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public Stuuser slogin(Stuuser stuuser) {
        System.out.println(stuuser);
        return loginMapper.getBynp(stuuser);
    }

    @Override
    public Teauser tlogin(Teauser teauser) {
        return loginMapper.tgetBynp(teauser);
    }
}
