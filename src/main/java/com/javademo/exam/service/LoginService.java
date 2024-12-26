package com.javademo.exam.service;


import com.javademo.exam.pojo.entity.Stuuser;
import com.javademo.exam.pojo.entity.Teauser;

public interface LoginService  {
    Stuuser slogin(Stuuser stuuser);
    Teauser tlogin(Teauser teauser);
}
