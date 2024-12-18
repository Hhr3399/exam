package com.javademo.exam.service;


import com.javademo.exam.pojo.Stuuser;
import com.javademo.exam.pojo.Teauser;

public interface LoginService  {
    Stuuser slogin(Stuuser stuuser);
    Teauser tlogin(Teauser teauser);
}
