package com.javademo.exam.mapper;


import com.javademo.exam.pojo.Question;
import com.javademo.exam.pojo.Stuexam;
import com.javademo.exam.pojo.Teauser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeaMapper {

    /**
     * 显示该课程所有考题
     *
     * @return
     */
    @Select("select * from question where course_id = #{courseId} ")
    List<Question> list(Integer courseId);

    /**
     * 插入考试题
     *
     * @param question
     */
    @Insert("insert into question(content,answer,single_score,course_id) values (#{content},#{answer},#{single_score},#{courseId})")
    void add(Question question);


    /**
     * 修改考试题
     *
     * @param question
     */
    @Update("update question set content = #{content},answer = #{answer} ,single_score=#{single_score},course_id = #{courseId} where id = #{id}")
    void update(Question question);

    /**
     * 删除考试题
     *
     * @param id
     */
    @Delete("delete from  question where id =#{id}")
    void delete(Integer id);

    /**
     * 查询最近一次的考试相关信息
     * @return
     */
    @Select("select e.id,s.student_name,c.course_name,e.score,e.stime,e.ftime from stuexam e,student s,course c\n" +
            "WHERE e.s_id=s.id and e.course_id=c.id and e.id IN (SELECT MAX(e.id)\n" +
            "             FROM stuexam e\n" +
            "             GROUP BY e.s_id, e.course_id)")
    List<Stuexam> listscore();


    /**
     * 老师编辑自己信息
     * @param teauser
     */
    @Update("update teacher set name = #{name},gender = #{gender} ,phonenumber=#{phonenumber},tid=#{tid},college_id=#{collegeId},course_id = #{courseId} where username = #{username}")
    void tupdate(Teauser teauser);

    /**
     * 某考生的课程考试情况
     * @param id
     * @return
     */
    @Select("select s.student_name,c.course_name,e.score,e.stime,e.ftime from stuexam e,student s,course c where e.s_id=s.id and e.course_id=c.id and e.s_id=1\n")
    List<Stuexam> getsexam(Integer id);
}
