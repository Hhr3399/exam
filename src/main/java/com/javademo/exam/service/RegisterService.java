package com.javademo.exam.service;

import com.javademo.exam.pojo.Stuuser;
import com.javademo.exam.pojo.Teauser;

public interface RegisterService {
    Stuuser get(Stuuser stuuser);

    void add(Stuuser stuuser);

    Teauser tget(Teauser teauser);

    void tadd(Teauser teauser);
}
