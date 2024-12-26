package com.javademo.exam.mapper;

import com.javademo.exam.pojo.entity.Course;
import com.javademo.exam.pojo.entity.Question;
import com.javademo.exam.pojo.entity.StudentCourse;
import com.javademo.exam.pojo.entity.Stuuser;
import com.javademo.exam.pojo.vo.ExamResultVo;
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

    @Select("select s.*,c.college_name from student s ,college c where s.id=#{id} and s.college_id=c.id")
    Stuuser gets(int id);

    @Select("select * from question where course_id=(select c.id from course c where course_name=#{courseName})")
    List<Question> getQuestions(String courseName);

    @Select("SELECT c.course_name from student s JOIN student_course sc ON s.id = sc.s_id JOIN course c ON sc.c_id = c.id WHERE s.id = #{id}")
    List<Course> gerCoursename(Stuuser stuuser);

    @Select("select student.student_name,course.course_name,student_course.score,student_course.stime,student_course.etime\n" +
            "from student_course,student,course\n" +
            "where student.id=student_course.s_id and course.id=student_course.c_id and student.id=#{id}")
    List<ExamResultVo> getexamResults(int id);

    @Select("select student.student_name,course.course_name,student_course.score,student_course.stime,student_course.etime\n" +
            "from student_course,student,course\n" +
            "where student.id=student_course.s_id and course.id=student_course.c_id and student.id=#{id} and course.course_name=#{courseName}")
    ExamResultVo getsigleResult(String courseName, Integer id);

    @Insert("insert into student_course ( s_id, c_id, score, stime, etime) values (#{sId},#{cId},#{score},#{stime},#{etime})")
    void saveResult(StudentCourse studentCourse);
}
