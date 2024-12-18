package com.javademo.exam.mapper;


import com.javademo.exam.pojo.entity.Question;
import com.javademo.exam.pojo.entity.Teauser;
import com.javademo.exam.pojo.vo.MyStudentResultVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeaMapper {

    /**
     * 老师查询自己的信息
     * @param id
     * @return
     */
    @Select("select t.id,t.username,t.password,t.name,t.gender,t.tid,t.phonenumber,c.course_name,ce.college_name,t.college_id,t.course_id from teacher t, course c,college ce  where t.id=#{id} and t.course_id=c.id and t.college_id=ce.id")
    Teauser gettea(Integer id);

    /**
     * 显示该课程所有考题
     *
     * @return
     */
    @Select("select q.* from question q,course c where c. course_name=#{courseName} and q.course_id=c.id ")
    List<Question> list(String courseName);

    /**
     * 插入考试题
     *
     * @param question
     */
    @Insert("insert into question(content,answer,single_score,course_id) values (#{content},#{answer},#{singleScore},#{courseId})")
    void add(Question question);


    /**
     * 修改考试题
     *
     * @param question
     */
    @Update("update question set content = #{content},answer = #{answer} ,single_score=#{singleScore} where id = #{id}")
    void update(Question question);

    /**
     * 删除考试题
     *
     * @param id
     */
    @Delete("delete from  question where id =#{id}")
    void delete(Integer id);

    /**
     * 老师编辑自己信息
     * @param teauser
     */
    @Update("update teacher set name = #{name},gender = #{gender} ,phonenumber=#{phonenumber},tid=#{tid},college_id=#{collegeId},course_id = #{courseId} where id = #{id}")
    void tupdate(Teauser teauser);

    @Select("SELECT s.student_name,\n" +
            "       s.gender,\n" +
            "       s.phonenumber,\n" +
            "       c.college_name,\n" +
            "       co.course_name,\n" +
            "       sc.score,\n" +
            "       sc.stime,\n" +
            "       sc.etime\n" +
            "FROM student_course sc\n" +
            "JOIN student s ON s.id = sc.s_id\n" +
            "JOIN course co ON co.id = sc.c_id\n" +
            "JOIN college c ON c.id = s.college_id\n" +
            "WHERE co.id = #{courseid}" +
            "GROUP BY s.student_name, s.gender, s.phonenumber, c.college_name, co.course_name, sc.score, sc.stime, sc.etime\n" +
            "ORDER BY  sc.score DESC")
    List<MyStudentResultVo> getStuResult(Integer courseid);


    @Select("SELECT s.student_name,\n" +
            "       s.gender,\n" +
            "       s.phonenumber,\n" +
            "       c.college_name,\n" +
            "       sc.score,\n" +
            "       co.course_name,\n" +
            "       sc.stime,\n" +
            "       sc.etime\n" +
            "FROM student_course sc\n" +
            "JOIN student s ON s.id = sc.s_id\n" +
            "JOIN course co ON co.id = sc.c_id\n" +
            "JOIN college c ON c.id = s.college_id\n" +
            "WHERE co.id = 1\n" +
            "  AND sc.score = (\n" +
            "      SELECT MAX(sc2.score)\n" +
            "      FROM student_course sc2\n" +
            "      JOIN student s2 ON s2.id = sc2.s_id\n" +
            "      WHERE s2.student_name = s.student_name\n" +
            "        AND sc2.c_id = co.id\n" +
            "  )\n" +
            "ORDER BY s.student_name, sc.score DESC;")
    List<MyStudentResultVo> getStuMaxResult(Integer courseid);
}
