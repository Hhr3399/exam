package com.javademo.exam.mapper;

import com.javademo.exam.pojo.Stuuser;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StuMapper {

    /**
     * 查询所有学生信息
     *
     * @return
     */

    @Select("select * from student ")
    List<Stuuser> list();

    /**
     * 学生可以自行修改一些个人信息
     */
    @Update("update student set name = #{name},gender = #{gender} ,phonenumber = #{phonenumber}," +
            "sid=#{sid},college_id=#{collegeId} where id = #{id}")
    void update(Stuuser stuuser);
}
