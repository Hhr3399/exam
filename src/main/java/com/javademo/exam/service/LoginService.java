package com.javademo.exam.service;


import com.javademo.exam.pojo.Stuuser;
import com.javademo.exam.pojo.Teauser;

public interface LoginService  {
    Stuuser login(Stuuser stuuser);
    Teauser login(Teauser teauser);
}
