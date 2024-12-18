package com.javademo.exam.service;

import com.javademo.exam.pojo.entity.Stuuser;
import com.javademo.exam.pojo.entity.Teauser;

public interface RegisterService {
    Stuuser get(Stuuser stuuser);

    void add(Stuuser stuuser);

    Teauser tget(Teauser teauser);

    void tadd(Teauser teauser);
}
