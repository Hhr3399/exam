package com.javademo.exam.mapper;

import com.javademo.exam.pojo.Stuuser;
import com.javademo.exam.pojo.Teauser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {


    @Select("select * from student where username=#{username} and password=#{password}")
    Stuuser getBynp(Stuuser stuuser);

    @Select("select * from student where username=#{username} and password=#{password}")
    Teauser getBynp(Teauser teauser);
}
