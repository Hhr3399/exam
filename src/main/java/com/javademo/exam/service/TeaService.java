package com.javademo.exam.service;

import com.javademo.exam.pojo.Question;
import com.javademo.exam.pojo.Stuexam;
import com.javademo.exam.pojo.Teauser;

import java.util.List;

public interface TeaService {

    List<Question> list();

    void add(  Question question);

    void update(Question question);

    void delete(Integer id);

    List<Stuexam> stuscore();
}
