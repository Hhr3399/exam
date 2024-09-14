package com.javademo.exam.mapper;

import com.javademo.exam.pojo.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StuMapper {


    /**
     * 学生可以自行修改一些个人信息
     */
    @Update("update student set student_name = #{studentName},gender = #{gender} ,phonenumber = #{phonenumber}," +
            "sid=#{sid},college_id=#{collegeId} where id = #{id}")
    void update(Stuuser stuuser);

    /**
     * 获取学生课程列表
     *
     * @param stuuser
     * @return
     */
    @Select("select student.student_name,course.course_name from student,course,student_course where student.id= student_course.s_id and course.id=student_course.c_id and student.id=#{id}")
    List<Test> courselist(Stuuser stuuser);


    @Select("select * from question q where id=#{id}")
    Question getquestion(Integer questionid);


    @Select("select s.*,c.college_name from student s ,college c where s.id=#{id} and s.college_id=c.id")
    Stuuser gets(Integer id);

    @Select("select * from question where course_id=(select c.id from course c where course_name=#{name})")
    List<Question> selecttest(String name);

    @Insert("insert into stuexam(score, stime, ftime, s_id, course_id)values\n" +
            "                                                                (#{score},\n" +
            "                                                                 #{stime},\n" +
            "                                                                 #{ftime},\n" +
            "                                                                 (select id from student s where s.student_name=#{studentName}),\n" +
            "                                                                 (select id from course c where c.course_name=#{courseName}))")
    void insertResult(Stuexam stuexam);

    @Select("select * from stuexam se where se.s_id = (select s.id from student s where s.student_name=#{studentName}) and se.course_id = (select c.id from course c where c.course_name=#{courseName})")
    List<Stuexam> selectStatus(Test t);


    @Select("select e.id,s.student_name,c.course_name,e.score,e.stime,e.ftime from stuexam e,student s,course c WHERE e.s_id = (select s.id from student s where s.student_name=#{studentName}) and e.course_id = (select c.id from course c where c.course_name=#{courseName}) and e.s_id=s.id and e.course_id=c.id and e.id IN (SELECT MAX(e.id) FROM stuexam e GROUP BY e.s_id, e.course_id)")
    Stuexam selectLatest(Test t);


    @Select("SELECT c.course_name from student s JOIN student_course sc ON s.id = sc.s_id JOIN course c ON sc.c_id = c.id WHERE s.id = #{id}")
    List<Course> gerCoursename(Stuuser stuuser);
}
