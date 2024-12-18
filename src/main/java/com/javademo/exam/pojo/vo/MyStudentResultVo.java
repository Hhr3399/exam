package com.javademo.exam.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class MyStudentResultVo {
    private String studentName;
    private String gender;
    private String phonenumber;
    private String collegeName;
    private String courseName;
    private int score;
    private LocalDateTime stime;
    private LocalDateTime etime;
}
