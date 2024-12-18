package com.javademo.exam.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    private Integer id;
    private String content;
    private String answer;
    private int singleScore;
    private int courseId;
    private String courseName;

}
