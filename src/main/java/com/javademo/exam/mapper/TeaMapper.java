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
     * @return
     */
    @Select("select * from question ")
    List<Question> list();

    /**
     * 插入考试题
     * @param question
     */
    @Insert("insert into question(content,answer,course_id) values (#{content},#{answer},#{courseId})")
    void add(Question question);


    /**
     * 修改考试题
     * @param question
     */
    @Update("update question set content = #{content},answer = #{answer} ,course_id = #{courseId} where id = #{id}")
    void update(Question question);

    /**
     * 删除考试题
     * @param id
     */
    @Delete("delete from  question where id =#{id}")
    void delete(Integer id);

    @Select("select * from question ")
    List<Stuexam> listscore();
}
