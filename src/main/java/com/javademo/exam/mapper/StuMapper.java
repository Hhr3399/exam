package com.javademo.exam.mapper;

import com.javademo.exam.pojo.Course;
import com.javademo.exam.pojo.Question;
import com.javademo.exam.pojo.Stuuser;
import com.javademo.exam.pojo.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StuMapper {


    /**
     * 学生可以自行修改一些个人信息
     */
    @Update("update student set student_name = #{student_name},gender = #{gender} ,phonenumber = #{phonenumber}," +
            "sid=#{sid},college_id=#{collegeId} where id = #{id}")
    void update(Stuuser stuuser);

    /**
     * 获取学生课程列表
     * @param stuuser
     * @return
     */
    @Select("select student.student_name,course.course_name from student,course,student_course " +
            "where student.id= student_course.s_id and course.id=student_course.c_id and student.id=#{id} ")
    List<Test> courselist(Stuuser stuuser);


    @Select("select q.id,q.content,q.single from question q where id=#{id}")
    Question getquestion(Integer questionid);
}
