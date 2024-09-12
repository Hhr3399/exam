package com.javademo.exam.service;

import com.javademo.exam.pojo.Stuuser;
import com.javademo.exam.pojo.Teauser;

public interface RegisterService {
    Stuuser get(Stuuser stuuser);

    void add(Stuuser stuuser);

    Teauser get(Teauser teauser);

    void add(Teauser teauser);
}
