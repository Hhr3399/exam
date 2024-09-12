package com.javademo.exam.mapper;

import com.javademo.exam.pojo.Stuuser;
import com.javademo.exam.pojo.Teauser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RegisterMapper {

    @Select("select * from student where username = #{username}")
    Stuuser get(Stuuser stuuser);


    @Insert("insert into student(username,password,name,gender,phonenumber,sid,college_id) " +
            "values (#{username},#{password},#{name},#{gender},#{phonenumber},#{sid},#{collegeId})")
    void add(Stuuser stuuser);


    @Select("select * from teacher where username = #{username}")
    Teauser get(Teauser teauser);


    @Insert("insert into teacher(username,password,name,gender,phonenumber,tid,college_id,course_id) " +
            "values (#{username},#{password},#{name},#{gender},#{phonenumber},#{tid},#{collegeId},#{courseId})")
    void add(Teauser teauser);
}
