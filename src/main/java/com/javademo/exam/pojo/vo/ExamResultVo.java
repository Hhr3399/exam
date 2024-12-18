package com.javademo.exam.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamResultVo {

    private Integer id;

    private String studentName;

    private String courseName;

    private Integer score;

    private LocalDateTime stime;

    private LocalDateTime etime;

}
