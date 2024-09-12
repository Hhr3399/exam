package com.javademo.exam.service;

import com.javademo.exam.pojo.Stuuser;

import java.util.List;

public interface StuService {
    List<Stuuser> list();

    void update(Stuuser stuuser);
}
